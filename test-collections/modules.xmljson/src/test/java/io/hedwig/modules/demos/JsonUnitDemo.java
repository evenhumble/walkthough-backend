package io.hedwig.modules.demos;

import com.sun.deploy.util.StringUtils;
import io.hedwig.modules.engine.result.DifferenceResult;
import io.hedwig.modules.engine.result.OverallResult;
import net.javacrumbs.jsonunit.JsonAssert;
import net.javacrumbs.jsonunit.core.Option;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUnitDemo {

//    JSON documents are different:
//    Different value found in node "responseCode", expected: <1> but was: <9043>.
//    Different keys found in node "result.attributes", expected: <[k1, k2, k4]> but was: <[k1, k2, k3]>. Missing: "result.attributes.k4" Extra: "result.attributes.k3"
//    Different value found in node "result.attributes.k2", expected: <"v2"> but was: <"v3422">.

    @Test
    public void testJSONCompare(){

        String json1="{\n" +
            "    \"responseCode\": 1,\n" +
            "    \"responseMessage\": \"succ\",\n" +
            "    \"result\":{\n" +
            "        \"attributes\": {\n" +
            "            \"k1\": \"v1\",\n" +
            "            \"k2\": \"v2\",\n" +
            "            \"k4\": \"v4\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
        String json2="{\n" +
            "    \"responseCode\": 9043,\n" +
            "    \"responseMessage\": \"succ\",\n" +
            "    \"result\":{\n" +
            "        \"attributes\": {\n" +
            "            \"k1\": \"v1123\",\n" +
            "            \"k2\": \"v3422\",\n" +
            "            \"k3\": \"v3422\"\n" +
            "        }\n" +
            "    }\n" +
            "}";


        JsonAssert.setOptions(Option.IGNORING_ARRAY_ORDER,
            Option.TREATING_NULL_AS_ABSENT);
        try {
            JsonAssert.assertJsonEquals(json1, json2,
                JsonAssert.whenIgnoringPaths("result.attributes.k1"));
        }catch (AssertionError e){
            System.out.println(e.getMessage());
            String msg = e.getMessage();
            msg= msg.replaceAll("JSON documents are different:","").trim();
            String[] splittedStr = msg.split("\n");
            List<DifferenceResult> resultList = new ArrayList<>();
            for (String s : splittedStr) { //0: different Node,//1 expected value, 2 read value 3 Missing Node,4.Extra node
               if(s.trim().length()>1) {
                   String[] result = s.split("Different value found in node |Different keys found in node |expected:|but was:|Missing:|Extra:");
                   List<String> trimedResult = new ArrayList<>();
                   for (String s1 : result) {
                       if(s1.length()>=1){
                           trimedResult.add(s1);
                       }
                   }
                   DifferenceResult dResult = new DifferenceResult();
                   dResult.setNode(trimedResult.get(0).replaceAll("\"","").trim());
                   dResult.setSourceValue(trimedResult.get(1).replaceAll("<|>.","").trim());
                   dResult.setTestValue(trimedResult.get(2).replaceAll("<|>.","").trim());
                   if(trimedResult.size()>=4){
                       dResult.setMissingNode(trimedResult.get(3).replaceAll("\"","").trim());
                   }
                   if(trimedResult.size()==5){
                       dResult.setExtraNode(trimedResult.get(4).replaceAll("\"","").trim());
                   }
                   resultList.add(dResult);
               }
            }
            OverallResult overallResult = new OverallResult();
            overallResult.setResult(resultList);
            overallResult.setBaseLine(json1);
            overallResult.setBaseLine(json2);
            overallResult.setSame(false);

        }



    }
    @Test
    public void xmlToJson() throws IOException {

        String path = ClassLoader.getSystemResource("").getPath();
        File file = new File(path+"/output.xml");
        String xmlString = FileUtils.readFileToString(file,"utf-8");
        JSONObject result = XML.toJSONObject(xmlString);
        System.out.println(result);
    }
}
