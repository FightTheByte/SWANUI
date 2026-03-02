package customevents;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelectEventTest{

    SelectEvent selectEvent;

    @BeforeAll
    public void instantiate(){
        selectEvent = new SelectEvent();
    }

    @Test
    void selectEventTest(){
        assertTrue(selectEvent instanceof SelectEvent, "SelectEvent Object didn't instantiate");
    }
}
