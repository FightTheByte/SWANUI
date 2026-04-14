package org.example.cob.handlers;

import com.google.common.eventbus.Subscribe;
import org.example.cob.customevents.*;
import org.example.cob.eventbus.SwanEventBus;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

public class SwanUIHandlerTest {
    String name;
    String params;
    String error;
    AtomicReference<String> result = new AtomicReference<>();
    String fileResult;
    SwanUIHandler swanUIHandler;
    SwanProgramHandler swanProgramHandler;
    PoolHandler poolHandler;
    FileHandler fileHandler;

    public SwanUIHandlerTest(){
        swanUIHandler = new SwanUIHandler();
        poolHandler = new PoolHandler("test.db");
        fileHandler = new FileHandler();
        swanProgramHandler = new SwanProgramHandler(true);
        SwanEventBus.registerListener(poolHandler);
        SwanEventBus.registerListener(fileHandler);
        SwanEventBus.registerListener(swanProgramHandler);
        SwanEventBus.registerListener(new Listener());
        SwanEventBus.registerListener(new ErrorListener());
        SwanEventBus.registerListener(new StringListener());
        SwanEventBus.registerListener(new FileListener());
    }

    class Listener{
        @Subscribe
        void handleSelectEvent(ReturnSelectEvent returnSelectEvent){
            name = returnSelectEvent.getSelectResult().getLast().getName();
            params = returnSelectEvent.getSelectResult().getLast().getParameters();
        }
    }

    class ErrorListener{
        @Subscribe
        void handleErrorEvent(ErrorEvent errorEvent){
            error = errorEvent.getError();
        }
    }

    class StringListener {
        @Subscribe
        public void resultEvent(ReturnSwanResultEvent results){
            result.set(results.returnSwanResult());
            System.out.println(result.toString());
        }
    }

    class FileListener {
        @Subscribe
        public void fileResultEvent(FileResultEvent result){fileResult = result.returnResult();}
    }


    @Test
    void insertIntoDbTest(){
        swanUIHandler.insertToDB("test-name-ui", "test-param-ui");
        assertNull(error);
    }

    @Test
    void insertIntoDbErrorTest(){
        swanUIHandler.insertToDB("1234567890123456789012345678901234567890123456789012345678901234567890", "test-param-ui");
        assertTrue(Objects.equals(error, "Name too long"));
    }

    @Test
    void selectFromDbTest(){
        swanUIHandler.selectFromDB(new SelectEvent());

    }

    @Test
    void runSwanTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            swanUIHandler.runSwan();
        });
        thread.start();
        thread.join();
        assertTrue(Objects.equals(result.toString().trim(), "success"));
    }

    @Test
    void writeToFileTest(){
        swanUIHandler.writeToFile();
        assertTrue(Objects.equals(fileResult, "Successfully wrote parameters to file"));
    }
}
