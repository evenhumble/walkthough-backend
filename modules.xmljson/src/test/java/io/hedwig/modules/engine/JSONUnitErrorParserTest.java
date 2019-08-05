package io.hedwig.modules.engine;

import io.hedwig.modules.engine.result.DifferenceResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JSONUnitErrorParserTest {

    @Test
    public void testParse() {

        String errMessage = "JSON documents are different:\n" +
            "Different value found in node \"responseCode\", expected: <1> but was: <9043>.\n" +
            "Different value found in node \"result.attributes.k2\", expected: <\"v2\"> but was: <\"v3422\">.";
        List<DifferenceResult> result = JSONUnitErrorParser.parse(errMessage);

        Assert.assertEquals(result.size(),2);
        Assert.assertEquals(result.get(0).getNode(),"responseCode");
        Assert.assertEquals(result.get(0).getSourceValue(),"1");
        Assert.assertEquals(result.get(0).getTestValue(),"9043");
    }
}
