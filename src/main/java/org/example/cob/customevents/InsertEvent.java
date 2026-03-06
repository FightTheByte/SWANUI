package org.example.cob.customevents;

public class InsertEvent {
    String name;
    String parameters;

    public InsertEvent(String name, String parameters){
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
