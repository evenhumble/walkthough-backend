package io.qkits.corejava.corejava.eventbus;

import com.google.common.eventbus.EventBus;
import org.testng.annotations.Test;

/**
 * Created by patrick on 16/6/23.
 */
public class EventBusClient {

    @Test
    public void testReceiveEvent(){

        EventBus eventBus = new EventBus("test");
        TestEventListener listener = new TestEventListener();

        for (int i = 0; i <10 ; i++) {

            eventBus.post(new TestEvent(10+i));
            eventBus.post(new TestEvent(10+i+1));
        }

        System.out.println(listener.getLastMessage());
    }
}
