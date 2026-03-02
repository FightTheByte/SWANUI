package customevents;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileResultEventTest {

    FileResultEvent fileResultEvent;

    @BeforeAll
    public void instantiate(){
        fileResultEvent = new FileResultEvent("success");
    }

    @Test
    void fileResultEventTest(){
        String result = fileResultEvent.returnResult();
        assertEquals("success", result, "fileResultEvent Object returns the wrongs String");
    }

}
