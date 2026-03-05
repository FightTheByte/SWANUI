package org.example.cob.swan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SwanProcessBuilderTest {

    SwanProcessBuilder swanProcessBuilder;

    @BeforeEach
    public void instantiate(){
        swanProcessBuilder = new SwanProcessBuilder();
    }

    @Test
    void runModelTest(){
        String[] commands = new String[]{"cmd.exe", "/C", "echo pass"};
        try{
            String result = swanProcessBuilder.runSwan(commands);
            assertEquals("pass", result, "SwanProcessBuilder did not run or return result");
        } catch (RuntimeException e) {
            fail(e);
        }
    }
}
