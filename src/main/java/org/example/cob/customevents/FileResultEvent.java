package org.example.cob.customevents;

public class FileResultEvent {

    private String result;

    public FileResultEvent(String result) {
        this.result = result;
    }


    public String returnResult() {
        return result;
    }
}
