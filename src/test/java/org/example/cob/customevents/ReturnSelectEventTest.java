package org.example.cob.customevents;

import org.example.cob.util.Parameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnSelectEventTest {

    ReturnSelectEvent returnSelectEvent;


    @BeforeEach
    public void instantiate(){
        List<Parameter> result = new ArrayList<>();
        result.add(new Parameter("test-name", "test-param"));
        returnSelectEvent = new ReturnSelectEvent(result);
    }

    @Test
    public void returnSelectEventTest(){
        assertEquals("test-name", returnSelectEvent.getSelectResult().getFirst().getName(), "Did not return expected Array, with parameter object, not providing the correct method and name value");
        assertEquals("test-param", returnSelectEvent.getSelectResult().getFirst().getParameters(), "Did not return expected Array, with parameter object, not providing the correct method and param value");
    }
}
