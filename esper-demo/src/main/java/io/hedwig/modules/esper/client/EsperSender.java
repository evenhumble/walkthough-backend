package io.hedwig.modules.esper.client;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

import org.springframework.stereotype.Component;

/**
 * 1. author: patrick
 */
@Component
public class EsperSender {

    private EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();
    public void send(Object event){
        engine.getEPRuntime().sendEvent(event);
    }
}
