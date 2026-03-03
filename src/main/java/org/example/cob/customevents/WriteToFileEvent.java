package org.example.cob.customevents;

public class WriteToFileEvent {


    private String parameters;

    public WriteToFileEvent(String parameters) {
        this.parameters = parameters;
    }

    public String getParameters() {
        return parameters;
    }
}
