package org.example.cob.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ParameterTest {
    Parameter parameter;

    @BeforeEach
    public void instantiate(){
        parameter = null;
    }

    @Test
    void setsAndGetsNameTest(){
        parameter = new Parameter("name", "parameters");
        String name = parameter.getName();
        assertTrue(Objects.equals(name, "name"));
    }

    @Test
    void setsAndGetsParametersTest(){
        parameter = new Parameter("name", "parameters");
        String parameters = parameter.getParameters();
        assertTrue(Objects.equals(parameters, "parameters"));
    }
}
