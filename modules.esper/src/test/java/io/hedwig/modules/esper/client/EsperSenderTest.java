package io.hedwig.modules.esper.client;

import org.junit.Before;
import org.junit.Test;

import io.hedwig.modules.esper.entity.MarketData;
import io.hedwig.modules.esper.server.EsperServerSetup;

/**
 * 1. author: patrick
 */
public class EsperSenderTest {

    EsperSender sender;


    @Before
    public void setupEseprEngine(){
        EsperServerSetup.setupMarketEPL();
        sender=new EsperSender();
    }
    @Test
    public void test_sendMarkData(){
//        ExecutorService es  = Executors.newFixedThreadPool(100);
       sender.send(new MarketData("ticker1",12.00,1000));
    }

}
