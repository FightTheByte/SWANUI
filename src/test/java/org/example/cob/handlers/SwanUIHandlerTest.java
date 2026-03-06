package org.example.cob.handlers;

import com.google.common.eventbus.Subscribe;
import org.example.cob.customevents.ErrorEvent;
import org.example.cob.customevents.ReturnSelectEvent;
import org.example.cob.customevents.ReturnSwanResultEvent;
import org.junit.jupiter.api.Test;

public class SwanUIHandlerTest {
    String name;
    String params;
    String error;
    String result;

    class Listener{
        @Subscribe
        void handleSelectEvent(ReturnSelectEvent returnSelectEvent){
            name = returnSelectEvent.getSelectResult().getLast().getName();
            params = returnSelectEvent.getSelectResult().getLast().getParameters();
        }
    }

    class ErrorListener{
        @Subscribe
        void handleErrorEvent(ErrorEvent errorEvent){
            error = errorEvent.getError();
        }
    }

    class StringListener {
        @Subscribe
        public void resultEvent(ReturnSwanResultEvent results){result = results.returnSwanResult();}
    }

    @Test
    void insertIntoDbTest(){

    }

    @Test
    void selectFromDbTest(){

    }

    @Test
    void runSwanTest(){

    }

    @Test
    void writeToFileTest(){

    }
}
