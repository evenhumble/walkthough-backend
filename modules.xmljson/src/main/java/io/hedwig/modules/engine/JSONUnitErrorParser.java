package io.hedwig.modules.engine;

import io.hedwig.modules.engine.result.DifferenceResult;
import io.hedwig.modules.engine.result.OverallResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONUnitErrorParser {

    public static List<DifferenceResult> parse(String jsonUnitErrMsg) {
        if (jsonUnitErrMsg == null) return Collections.emptyList();
        if (jsonUnitErrMsg.length() <= 1) return Collections.emptyList();

        jsonUnitErrMsg= jsonUnitErrMsg.replaceAll("JSON documents are different:","").trim();
        String[] splittedStr = jsonUnitErrMsg.split("\n");
        List<DifferenceResult> resultList = new ArrayList<>();
        for (String s : splittedStr) { //0: different Node,//1 expected value, 2 read value 3 Missing Node,4.Extra node
            if(s.trim().length()>1) {
                String[] result = s.split("Different value found in node |Different keys found in node |expected:|but was:|Missing:|Extra:");
                List<String> trimmedResult = new ArrayList<>();
                for (String s1 : result) {
                    if(s1.length()>=1){
                        trimmedResult.add(s1);
                    }
                }
                DifferenceResult dResult = new DifferenceResult();
                dResult.setNode(trimmedResult.get(0).replaceAll("\"","")
                    .replaceAll(",","").trim());
                dResult.setSourceValue(trimmedResult.get(1).replaceAll("<|>.","").trim());
                dResult.setTestValue(trimmedResult.get(2).replaceAll("<|>.","").trim());
                if(trimmedResult.size()>=4){
                    dResult.setMissingNode(trimmedResult.get(3).replaceAll("\"","").trim());
                }
                if(trimmedResult.size()==5){
                    dResult.setExtraNode(trimmedResult.get(4).replaceAll("\"","").trim());
                }
                resultList.add(dResult);
            }
        }

        return resultList;

    }
}
