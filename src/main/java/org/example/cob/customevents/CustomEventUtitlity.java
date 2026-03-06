package org.example.cob.customevents;

public class CustomEventUtitlity {
    private CustomEventUtitlity(){};

    public static WriteToFileEvent getWriteToFileEvent(String parameters){
        return new WriteToFileEvent(parameters);
    }

    public static FileResultEvent getFileResultEvent(String result){
        return new FileResultEvent(result);
    }

    public static InsertEvent getInsertEvent(String name, String parameters){
        return new InsertEvent(name, parameters);
    }

    public static SelectEvent getSelectEvent(){
        return new SelectEvent();
    }

    public static CallSwanEvent getCallSwanEvent(){
        return new CallSwanEvent();
    }

    public static ReturnSwanResultEvent getReturnSwanResultEvent(String result){
        return new ReturnSwanResultEvent(result);
    }

    public static ErrorEvent getErrorEvent(String error){
        return new ErrorEvent(error);
    }
}
