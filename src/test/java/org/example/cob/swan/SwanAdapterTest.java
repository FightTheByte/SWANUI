package org.example.cob.swan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwanAdapterTest {
    SwanAdapter swanAdapter;
    String result;

    @BeforeEach
    public void instantiate(){
        swanAdapter = new SwanAdapter();
    }

    @Test
    void runSwanWithArgumentsTest(){
        if(System.getProperty("os.name").startsWith("Windows")){
            result = swanAdapter.runSwanWithArgumentsWindows();
        }
        else if(System.getProperty("os.name").startsWith("Mac")){
            result = swanAdapter.runSwanWithArgumentsMac();
        }
        else{
            result = swanAdapter.runSwanWithArgumentsLinux();
        }

        assertEquals("success", result.trim(), "SwanAdapter did not return expected result");
    }
}
