package io.hedwig.modules.esper.metrics;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * 1. author: patrick
 */
public class EventCounterListener
    extends AbstractEsperEventCounter implements UpdateListener {

    @Override
    public void update(EventBean[] eventBeans, EventBean[] eventBeans1) {
//        System.out.println(eventBeans);
//        System.out.println(eventBeans1);
//        System.out.println(eventBeans[0].getUnderlying());
        super.increment();
//        System.out.println(this.getCounter());
    }
}
