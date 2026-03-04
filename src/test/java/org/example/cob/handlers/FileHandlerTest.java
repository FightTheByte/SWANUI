package org.example.cob.handlers;

import com.google.common.eventbus.EventBus;
import org.example.cob.customevents.WriteToFileEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.cob.handlers.FileHandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FileHandlerTest {

    FileHandler fileHandler;
    File file;
    EventBus eventBus;

    @BeforeEach
    public void instantiate() throws IOException {
        fileHandler = new FileHandler();
        eventBus = new EventBus();
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
}
