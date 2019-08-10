package io.hedwig.modules.clients;

import io.hedwig.modules.engine.JSONChecker;
import io.hedwig.modules.engine.result.OverallResult;
import org.junit.Test;

import java.io.IOException;
public class PataClientsTest {

    @Test
    public void testGetPataClientByIpPort() throws IOException {
        String sourceUrl = "http://qcptmst.ppdapi.com/api_model?mark=pata-master";
        String testUrl = "http://qcptmst.ppdapi.com/api_model?mark=pata-master";
        String sourceRequest = "{\"Info\": {\"AppVer\": \"2.0.1\", \"AppId\": \"pataq\"}, \"Data\": {\"DataModel\": [{\"userid\":57723918, \"bizid\": 4001, \"listingid\":-1}]}}";
        String testRequest = "{\"Info\": {\"AppVer\": \"2.0.1\", \"AppId\": \"pataq\"}, \"Data\": {\"DataModel\": [{\"userid\":57723918, \"bizid\": 4001, \"listingid\":-1}]}}";
        String sourceResponse = PataClients.getNewPetaResponse(sourceUrl,sourceRequest);
        String testResponse = PataClients.getNewPetaResponse(testUrl,testRequest);

        OverallResult comparedResult  = new JSONChecker().check(sourceResponse,testResponse);
        System.out.println(comparedResult);
    }

    @Test
    public void testGetPataClientByDomainUrl() throws IOException {

    }
}
