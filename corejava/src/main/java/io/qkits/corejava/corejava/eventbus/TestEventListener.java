package io.qkits.corejava.corejava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Created by patrick on 16/6/23.
 */
public class TestEventListener {

    public int lastMessage=0;

    @Subscribe
    public void listen(TestEvent e){
        lastMessage = e.getMessageId();
        System.out.println("message:"+this.lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
