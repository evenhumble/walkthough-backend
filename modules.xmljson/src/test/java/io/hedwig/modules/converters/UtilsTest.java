package io.hedwig.modules.converters;

import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;


public class UtilsTest {

    @Test
    public void xmlToJson() throws IOException {
        String path = ClassLoader.getSystemResource("").getPath();
        String result= Utils.xmlFileToJson(path+"/output.xml");
        Assert.assertNotNull(result);
    }
}
