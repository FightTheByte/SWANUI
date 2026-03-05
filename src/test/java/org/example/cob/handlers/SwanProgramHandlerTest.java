package org.example.cob.handlers;


import com.google.common.eventbus.Subscribe;
import org.example.cob.customevents.ReturnSwanResultEvent;
import org.example.cob.eventbus.SwanEventBus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwanProgramHandlerTest {

    String result;
    SwanProgramHandler swanProgramHandler;

    @BeforeEach
    public void initialise(){
        swanProgramHandler = SwanProgramHandler.getInstance(true);
        SwanEventBus.registerListener(new StringListener());
    }

    class StringListener {
        @Subscribe
        public void resultEvent(ReturnSwanResultEvent results){result = results.returnSwanResult();}
    }

    @Test
    void testRunSwan() throws InterruptedException {
        Thread test = new Thread(() -> {
            try {
                swanProgramHandler.runSwan();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        test.start();
        do {

        }while(test.isAlive());
        assertEquals("success", result.trim(), "swanProgramHandler did not emit and return correct String");
    }





}
