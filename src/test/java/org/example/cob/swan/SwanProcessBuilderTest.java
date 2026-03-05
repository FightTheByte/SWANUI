package org.example.cob.swan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SwanProcessBuilderTest {

    SwanProcessBuilder swanProcessBuilder;
    String[] arguments;

    @BeforeEach
    public void instantiate(){
        swanProcessBuilder = new SwanProcessBuilder();
    }

    @Test
    void runModelTest(){
        if(System.getProperty("os.name").startsWith("Windows")) {
            arguments = new String[]{"cmd", "/C", "echo success"};
        }
        else if(System.getProperty("os.name").startsWith("Mac")){
            arguments = new String[]{"/bin/bash", "-c", "echo success"};
        }
        else{
            arguments = new String[]{"/usr/bin/bash", "-c", "echo success"};
        }
        try{
            String results = swanProcessBuilder.runModel(arguments);
            assertEquals("success", results.trim(), "SwanProcessBuilder did not run or return result");
        } catch (RuntimeException e) {
            fail(e);
        }
    }
}
