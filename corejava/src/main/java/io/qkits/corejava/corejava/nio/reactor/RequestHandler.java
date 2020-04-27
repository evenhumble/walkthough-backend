package io.qkits.corejava.corejava.nio.reactor;

/**
 * @author mazhiqiang
 */
public class RequestHandler extends Thread {

    private Integer requestId;
    private Integer resourceId;
    private Dispatcher dispatcher;

    public RequestHandler(Integer requestId, Integer resourceId, Dispatcher dispatcher) {
        this.requestId = requestId;
        this.resourceId = resourceId;
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        System.out.println(String.format("The request No.[%s] is handling request with resource:[%s]", requestId, resourceId));
        //simulate actual execute process.
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("The request No.[%s] is processing request with resource:[%s]", requestId, resourceId));
        dispatcher.freeResource(resourceId);
        System.out.println(String.format("System resource:[%s] is released!", resourceId));
    }
}
