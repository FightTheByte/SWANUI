package org.example.cob.customevents;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WriteToFileEventTest {

    WriteToFileEvent writeToFileEvent;

    @BeforeEach
    public void instantiate(){
        writeToFileEvent = new WriteToFileEvent("this is a test");
    }

    @Test
    void testWriteToFileEvent(){

        String parameters = writeToFileEvent.getParameters();

        assertEquals("this is a test", parameters, "WriteToFileEvent wrong parameters" );
    }


}
