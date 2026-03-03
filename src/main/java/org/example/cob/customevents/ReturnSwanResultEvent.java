package org.example.cob.customevents;

public class ReturnSwanResultEvent {
    private String result;

    public ReturnSwanResultEvent(String result){
        this.result = result;
    }

    public String returnSwanResult(){
        return result;
    }
}
