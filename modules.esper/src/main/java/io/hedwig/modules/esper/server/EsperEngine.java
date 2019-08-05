package io.hedwig.modules.esper.server;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

/**
 * 1. author: patrick
 */
public class EsperEngine {

    private EPServiceProvider engine = EPServiceProviderManager.getDefaultProvider();

    public EsperEngine addEventType(Class eventType) {
        engine.getEPAdministrator().getConfiguration()
            .addEventType(
                eventType
            );

        return this;
    }

    public EPStatement createStatement(String epl) {
        return this.engine.getEPAdministrator().createEPL(epl);
    }

    public EPStatement createStatement(String epl, UpdateListener esperListener) {
        EPStatement statement = this.engine.getEPAdministrator().createEPL(epl);
        statement.addListener(esperListener);
        return statement;
    }

    public EsperEngine registerEPL(String epl) {
        this.engine.getEPAdministrator().createEPL(epl);
        return this;
    }
    public EPStatement registerEPL(String epl, UpdateListener esperListener) {
        EPStatement statement = this.engine.getEPAdministrator().createEPL(epl);
        statement.addListener(esperListener);
        return statement;
    }
    public EsperEngine registerEventType(Class eventType) {
        engine.getEPAdministrator().getConfiguration()
            .addEventType(
                eventType
            );

        return this;
    }
}
