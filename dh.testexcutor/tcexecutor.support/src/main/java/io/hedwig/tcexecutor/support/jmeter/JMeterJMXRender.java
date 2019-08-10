package io.hedwig.tcexecutor.support.jmeter;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class JMeterJMXRender {

    public static void main(String[] args) throws FileNotFoundException {
        ApiDesc apiDesc = new ApiDesc();
        apiDesc.setApiName("baidu-get-api");
        apiDesc.setDomain("www.baidu.com");
        apiDesc.setMethod("GET");
        apiDesc.setPath("/");
        JMeterScenario scenario = new JMeterScenario();
        scenario.setThreadNum("10");
        scenario.setDuration(60);
        scenario.setApiDesc(apiDesc);
        scenario.setScenarioName("baidu-10-60");

        JtwigTemplate template = JtwigTemplate.classpathTemplate("template/single.jmx");
        JtwigModel model = JtwigModel.newModel().with("scenario",scenario);

        template.render(model,new FileOutputStream(new File("First.jmx")));

        System.out.println("....END..........");

        List<JMeterScenario> scenarios = new ArrayList<>();
        scenarios.add(scenario);

        JMeterScenario s2 = new JMeterScenario();
        s2.setThreadNum("20");
        s2.setDuration(60);
        s2.setApiDesc(apiDesc);
        s2.setScenarioName("baidu-20-60");
        scenarios.add(s2);

        JtwigModel models = JtwigModel.newModel().with("scenarios"
                        ,scenarios);
        JtwigTemplate template1 = JtwigTemplate.classpathTemplate("template/scenario.jmx");
        template1.render(models,new FileOutputStream(new File("scenarios.jmx")));


    }
}
