package io.hedwig.tcexecutor.benchmark;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

public class VelocityRender extends BaseBenchmark {

    private VelocityContext context;
    private Template template;
    private static final String templateName = "templates/jmeter_v.jmx";

    @Setup
    public void setup() {
        Properties configuration = new Properties();
        configuration.setProperty("resource.loader", "class");
        configuration.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityEngine engine = new VelocityEngine(configuration);
        context = new VelocityContext(getContext());
        template = engine.getTemplate(templateName, "UTF-8");
    }

    @Benchmark
    public String benchmark() {
        Writer writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

}
