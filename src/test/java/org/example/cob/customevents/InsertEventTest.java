package org.example.cob.customevents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InsertEventTest {

    InsertEvent insertEvent;

    @BeforeEach
    public void instantiate(){
        insertEvent = new InsertEvent("name", "parameters");
    }

    @Test
    void insertEventTest(){
        assertTrue(insertEvent instanceof InsertEvent, "insertEvent Object not instantiated");
    }
}
