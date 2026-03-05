package org.example.cob.customevents;

public class ReturnSelectEvent {
    String selectResult;

    public ReturnSelectEvent(String selectResult){
        this.selectResult = selectResult;
    }

    public String getSelectResult(){
        return selectResult;
    }
}
