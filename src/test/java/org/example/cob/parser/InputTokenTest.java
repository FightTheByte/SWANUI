package org.example.cob.parser;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputTokenTest {

    InputToken inputToken;

    @BeforeEach
    public void instantiate(){
        inputToken = new InputToken(
                "TYPE",
                "NAME",
                "LABEL",
                0,
                1
        );
    }

    @Test
    void inputTokenTypeTest(){

        String type = inputToken.getType();
        assertEquals("TYPE", type);
    }

    @Test
    void inputTokenNameTest(){

        String name = inputToken.getName();
        assertEquals("NAME", name);
    }

    @Test
    void inputTokenLabelTest(){

        String label = inputToken.getLabel();
        assertEquals("LABEL", label);
    }

    @Test
    void inputTokenMinTest(){

        int min = inputToken.getMin();
        assertEquals(0, min);
    }

    @Test
    void inputTokenMaxTest(){

        int max = inputToken.getMax();
        assertEquals(1, max);
    }
}
