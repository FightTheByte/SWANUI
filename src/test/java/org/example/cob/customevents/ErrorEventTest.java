package org.example.cob.customevents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.cob.customevents.ErrorEvent;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErrorEventTest {

    ErrorEvent errorEvent;

    @BeforeEach
    public void instantiate(){
        errorEvent = new ErrorEvent("error");
    }

    @Test
    void createsInstanceTest(){
        assertTrue(errorEvent instanceof ErrorEvent);
    }

    @Test
    void getErrorTest(){
        String error = errorEvent.getError();
        assertTrue(Objects.equals(error, "error"));
    }
}
