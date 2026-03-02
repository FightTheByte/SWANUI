package customevents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReturnSwanResultEventTest {

    ReturnSwanResultEvent returnSwanResultEvent;

    @BeforeEach
    public void instantiate(){
        returnSwanResultEvent = new ReturnSwanResultEvent("result");
    }

    @Test
    void returnSwanResultEventTest(){
        assertTrue("result", returnSwanResultEvent.returnSwanResult(), "Did not return expected String result");
    }
}
