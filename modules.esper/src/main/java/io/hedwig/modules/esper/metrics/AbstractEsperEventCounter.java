package io.hedwig.modules.esper.metrics;

/**
 * 1. author: patrick 2. esper event counter
 */
public class AbstractEsperEventCounter {

    private int counter = 0; //todo: think about the AtomicInteger


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    protected void increment() {
        this.counter++;
    }
}
