package io.qkits.corejava.corejava.nio.reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author mazhiqiang
 */
public class DemultiPlexer {

    private ArrayBlockingQueue<Integer> resources = new ArrayBlockingQueue<Integer>(2);
    private List<Integer> requests = new ArrayList<Integer>();

    {
        resources.add(1);
        resources.add(2);
    }

    public synchronized Integer getResource(long s, long timeOut) {
        while (true) {
            if ((System.currentTimeMillis() - s) / 1000 > timeOut) {
                throw new RuntimeException("Time out to get Resource!");
            } else if (resources.size() > 0) {
                return resources.poll();
            }

            try{
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void returnResource(Integer i) {
        synchronized (resources) {
            resources.add(i);
        }
    }

    public void accept(Integer requestId) {
        requests.add(requestId);
        Integer resource = getResource(System.currentTimeMillis(), 5);
        Dispatcher dispatcher = new Dispatcher(requestId, resource, this);
        dispatcher.createRequestHandler().start();
    }

}
