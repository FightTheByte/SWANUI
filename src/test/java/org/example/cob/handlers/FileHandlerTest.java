package org.example.cob.handlers;

import com.google.common.eventbus.EventBus;
import org.example.cob.customevents.WriteToFileEvent;
import org.example.cob.eventbus.SwanEventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FileHandlerTest {

    FileHander fileHander;
    File file;
    EventBus eventBus;

    @BeforeEach
    public void instantiate(){
        fileHandler = new FileHandler();
        eventBus = new EventBus();
    }

    @Test
    void writeToFileTest(){
        fileHander.writeToFile("this is a test");
        file = new File("parameters.dat");
        try(Scanner scanner = new Scanner(file)){
            String data = scanner.nextLine();
            assertEquals("this is a test", data, "FileHandler did not write to file correctly");
        } catch(FileNotFoundException e){
            fail("FileHandler did not write to file correctly");
        }
    }

    @Test
    void subscribeAndPublishFileHandlerTest(){
        eventBus.register(fileHander);
        eventBus.post(new WriteToFileEvent("this is test2"));
        file = new File("parameters.dat");
        try(Scanner scanner = new Scanner(file)){
            String data = scanner.nextLine();
            assertEquals("this is a test2", data, "FileHandler did not write to file correctly");
        } catch(FileNotFoundException e){
            fail("FileHandler did not write to file correctly");
        }
    }
}
