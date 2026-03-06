package org.example.cob.customevents;

import org.example.cob.util.Parameter;

import java.util.List;

public class ReturnSelectEvent {
    private List<Parameter> selectResult;

    public ReturnSelectEvent(List<Parameter> selectResult){
        this.selectResult = selectResult;
    }

    public List<Parameter> getSelectResult(){
        return selectResult;
    }
}
