package customevents;

public class ReturnSwanResultEvent {
    private String result;

    ReturnSwanResultEvent(String result){
        this.result = result;
    }

    public String returnSwanResult(){
        return result;
    }
}
