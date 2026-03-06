package org.example.cob.handlers;

import org.example.cob.customevents.CallSwanEvent;
import org.example.cob.customevents.InsertEvent;
import org.example.cob.customevents.SelectEvent;
import org.example.cob.customevents.WriteToFileEvent;
import org.example.cob.eventbus.SwanEventBus;

public class SwanUIHandler {
    public void insertToDB(String name, String parameters) {
        SwanEventBus.returnEventBus().post(new InsertEvent(name, parameters));
    }

    public void selectFromDB(SelectEvent selectEvent) {
        SwanEventBus.returnEventBus().post(new SelectEvent());
    }

    public void runSwan() {
        SwanEventBus.returnEventBus().post(new CallSwanEvent());
    }

    public void writeToFile() {
        SwanEventBus.returnEventBus().post(new WriteToFileEvent("parameters"));
    }
}
