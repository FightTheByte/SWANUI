package org.example.cob.customevents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectEventTest{

    SelectEvent selectEvent;

    @BeforeEach
    public void instantiate(){
        selectEvent = new SelectEvent();
    }

    @Test
    void selectEventTest(){
        assertTrue(selectEvent instanceof SelectEvent, "SelectEvent Object didn't instantiate");
    }
}
