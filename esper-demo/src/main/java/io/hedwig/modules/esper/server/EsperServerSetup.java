package io.hedwig.modules.esper.server;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import io.hedwig.modules.esper.entity.MarketData;
import io.hedwig.modules.esper.metrics.EventCounterListener;

/**
 * 1. author: patrick
 */
@Component
public class EsperServerSetup {

    @PostConstruct
    public static void setupMarketEPL() {
        EsperEngine esperEngine = new EsperEngine();
        esperEngine.addEventType(MarketData.class);
        esperEngine.registerEPL("select * from MarketData",
                                new EventCounterListener());
    }
}
