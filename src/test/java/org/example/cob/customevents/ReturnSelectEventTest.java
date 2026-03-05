package org.example.cob.customevents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.JsonObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnSelectEventTest {

    ReturnSelectEvent returnSelectEvent;


    @BeforeEach
    public void instantiate(){
        returnSelectEvent = new ReturnSelectEvent("result");
    }

    @Test
    public void returnSwanResultEventTest(){
        assertEquals("result", returnSelectEvent.getSwanResult(), "Did not return expected String result");
    }
}
