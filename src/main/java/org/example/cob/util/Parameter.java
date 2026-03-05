package org.example.cob.util;

public class Parameter {
    private final String name;
    private final String parameters;

    public Parameter(String name, String parameters){
        this.name = name;
        this.parameters = parameters;
    }

    public String getName(){
        return name;
    }

    public String getParameters(){
        return parameters;
    }
}
