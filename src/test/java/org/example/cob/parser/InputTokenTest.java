package org.example.cob.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputTokenTest {

    InputToken inputToken;

    @BeforeEach
    public void instatiate(){
        inputToken = new InputToken("NAME", "TYPE");
    }

    @Test
    void inputTokenHasTypeTest(){
        type = inputToken.getType();
    }

    @Test
    void inputTokenHasNameTest(){
        name = inputToken.getName();
    }
}
