package io.hedwig.modules.engine;

import io.hedwig.modules.engine.result.OverallResult;
import net.javacrumbs.jsonunit.JsonAssert;
import net.javacrumbs.jsonunit.core.Option;


import java.util.ArrayList;
import java.util.List;

public class JSONChecker implements Checker {

    List<Option> optionsList = new ArrayList<>();


    @Override
    public OverallResult check(String baseLine,
                               String testLine,
                               CheckConfiguration checkConfig) {
        OverallResult result = new OverallResult();
        result.setBaseLine(baseLine);
        result.setTestLine(testLine);
        try {
            JsonAssert.assertJsonEquals(baseLine, testLine,
                checkConfig.getJsonConfig());
        }catch (AssertionError e){
          result.setResult(JSONUnitErrorParser.parse(e.getMessage()));
          result.setSame(false);
        }
        result.setSame(true);
        return result;
    }

    public OverallResult check(String baseLine,
                               String testLine
                               ) {
        OverallResult result = new OverallResult();
        result.setBaseLine(baseLine);
        result.setTestLine(testLine);
        try {
            JsonAssert.assertJsonEquals(baseLine, testLine);
        }catch (AssertionError e){
            result.setResult(JSONUnitErrorParser.parse(e.getMessage()));
            result.setSame(false);
            return result;
        }
        result.setSame(true);
        return result;
    }

    public JSONChecker options(Option...options){
        optionsList.addAll(optionsList);
        JsonAssert.setOptions(null ,options);
        return this;
    }

}
