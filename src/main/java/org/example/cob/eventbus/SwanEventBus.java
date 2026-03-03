package org.example.cob.eventbus;
import com.google.common.eventbus.EventBus;

public class SwanEventBus {
    private static final EventBus eventbus = new EventBus();

    private SwanEventBus(){}

    public static void registerListener(Object object){
        eventbus.register(object);
    }

    public static EventBus returnEventBus(){
        return eventbus;
    }
}
