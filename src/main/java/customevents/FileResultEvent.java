package customevents;

public class FileResultEvent {

    String result;

    public FileResultEvent(String result) {
        this.result = result;
    }


    public String returnResult() {
        return result;
    }
}
