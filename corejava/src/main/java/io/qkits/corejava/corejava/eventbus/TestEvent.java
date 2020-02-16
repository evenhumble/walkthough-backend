package io.qkits.corejava.corejava.eventbus;

/**
 * Created by patrick on 16/6/23.
 */
public class TestEvent {

    private final int messageId;

    public TestEvent(int messageId) {
        this.messageId = messageId;
        System.out.println("this message id is "+this.messageId);
    }

    public int getMessageId(){
        return this.messageId;
    }


}
