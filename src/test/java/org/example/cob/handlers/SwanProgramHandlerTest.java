package org.example.cob.handlers;


import com.google.common.eventbus.Subscribe;
import org.example.cob.customevents.ReturnSwanResultEvent;
import org.example.cob.eventbus.SwanEventBus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwanProgramHandlerTest {

    String result;
    SwanProgramHandler swanProgramHandler;

    @BeforeEach
    public void initialise(){
        swanProgramHandler = SwanProgramHandler.getInstance();
        SwanEventBus.registerListener(new StringListener());
    }

    class StringListener {
        @Subscribe
        public void resultEvent(ReturnSwanResultEvent results){result = results.returnSwanResult();}
    }

    @Test
    void testRunSwan(){
        swanProgramHandler.runSwan();
        assertEquals("success", result, "swanProgramHandler did not emit and return correct String");
    }





}
