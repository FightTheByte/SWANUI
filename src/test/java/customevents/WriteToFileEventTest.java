package customevents;


import org.example.customevents.WriteToFileEvent;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriteToFileEventTest {

    WriteToFileEvent writeToFileEvent;

    @Before
    public void instantiate(){
        writeToFileEvent = new WriteToFileEvent("this is a test");
    }

    @Test
    void testWriteToFileEvent(){

        String parameters = writeToFileEvent.getParameters();

        assertEquals("this is a test", parameters, "WriteToFileEvent wrong parameters" );
    }


}
