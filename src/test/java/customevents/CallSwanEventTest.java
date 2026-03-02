package customevents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CallSwanEventTest {

    CallSwanEvent callSwanEvent;

    @BeforeEach
    public void instantiate(){
        callSwanEvent = new CallSwanEvent();
    }

    @Test
    void callSwanEventTest(){
        assertTrue(callSwanEvent instanceof CallSwanEvent, "CallSwanEvent did not instantiate");
    }
}
