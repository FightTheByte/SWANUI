package org.example.cob.parser;

public class InputToken {

    String type;
    String name;

    public InputToken(String name, String type) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
