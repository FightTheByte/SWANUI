package org.example.cob.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SwanParameterFieldTest {

    SwanParameterField swanParameterField;

    @BeforeEach
    public void instantiate(){
        this.swanParameterField = new SwanParameterField("/swan-test.csv");
    }

    @Test
    void isInstantiated(){
        assertTrue(swanParameterField != null);
    }

    @Test
    void createsInputTokensWithoutCrashingTest(){
        try{
            swanParameterField.createInputTokens();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getInputTokensTest(){
        swanParameterField.createInputTokens();
        List<List<List<List<InputToken>>>> inputTokens = swanParameterField.getInputTokens();
        InputToken inputToken = inputTokens.getFirst().getFirst().getFirst().getFirst();
        int max = inputToken.getMax();
        assertEquals(1, max);
    }
}
