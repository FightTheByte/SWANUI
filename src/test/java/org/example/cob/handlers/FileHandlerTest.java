package org.example.cob.handlers;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.example.cob.eventbus.SwanEventBusTest;
import org.example.cob.handlers.FileHandler;
import org.example.cob.customevents.FileResultEvent;
import org.example.cob.customevents.WriteToFileEvent;
import org.example.cob.eventbus.SwanEventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    FileHandler fileHandler;
    File file;
    EventBus eventBus;
    int eventsHandled;

    class Listener {
        @Subscribe
        public void handleDeadEvent(DeadEvent deadEvent) {
            eventsHandled++;
        }
    }

    @BeforeEach
    public void instantiate() throws IOException {
        fileHandler = new FileHandler();
        eventBus = new EventBus();
        eventsHandled = 0;
    }

    @Test
    void subscribeAndPublishFileHandlerTest(){
        eventBus.register(fileHandler);
        eventBus.post(new WriteToFileEvent("this is a test2"));
        file = new File("parameters.dat");
        try(Scanner scanner = new Scanner(file)){
            String data = scanner.nextLine();
            assertEquals("this is a test2", data, "FileHandler did not write to file correctly");
        } catch(FileNotFoundException e){
            fail(e);
        }
    }

    @Test
    void publishFileResultEvent(){
        SwanEventBus.registerListener(new Listener());
        fileHandler.fileResultEvent("success");
        assertTrue(eventsHandled == 1, "Event didn't fire");
    }
}
