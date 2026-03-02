package customevents;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomEventUtitlityTest {

    @Test
    void returnsWriteToFileEventTest(){
        assertTrue(CustomEventUtitlity.getWriteToFileEvent() instanceof WriteToFileEvent, "Didn't return WriteToFileEvent Object");
    }

    @Test
    void returnsFileResultEventTest(){
        assertTrue(CustomEventUtitlity.getFileResultEvent() instanceof FileResultEvent, "Didn't return FileResultEvent Object");
    }

    @Test
    void returnsInsertEventTest(){
        assertTrue(CustomEventUtitlity.getInsertEvent() instanceof InsertEvent, "Didn't return InsertEvent Object");
    }

    @Test
    void returnsSelectEventTest(){
        assertTrue(CustomEventUtitlity.getSelectEvent() instanceof SelectEvent, "Didn't return SelectEvent Object");
    }

    @Test
    void returnsCallSwanEventTest(){
        assertTrue(CustomEventUtitlity.getCallSwanEvent() instanceof CallSwanEvent, "Didn't return CallSwanEvent Object");
    }

    @Test
    void returnsReturnSwanResultEventTest(){
        assertTrue(CustomEventUtitlity.getReturnSwanResultEvent() instanceof ReturnSwanResultEvent, "Didn't return ReturnSwanResultEvent Object");
    }
}
