package EventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import customevents.CallSwanEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwanEventBusTest {

    int eventsHandled = 0;

    @BeforeEach
    public void resetEventsHandled(){
        this.eventsHandled = 0;
    }

    class Listener {
        @Subscribe
        public void handleDeadEvent(DeadEvent deadEvent) {
            eventsHandled++;
        }
    }

    @Test
    void testSwanEventBus(){
        SwanEventBus.returnEventBus().register(new Listener());
        SwanEventBus.returnEventBus().post(new CallSwanEvent());
        assertTrue(eventsHandled == 1, "Event didn't fire");
    }


}
