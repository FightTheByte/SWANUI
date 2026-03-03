package org.example.cob.customevents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReturnSwanResultEventTest {

    ReturnSwanResultEvent returnSwanResultEvent;

    @BeforeEach
    public void instantiate(){
        returnSwanResultEvent = new ReturnSwanResultEvent("result");
    }

    @Test
    public void returnSwanResultEventTest(){
        assertEquals("result", returnSwanResultEvent.returnSwanResult(), "Did not return expected String result");
    }
}
