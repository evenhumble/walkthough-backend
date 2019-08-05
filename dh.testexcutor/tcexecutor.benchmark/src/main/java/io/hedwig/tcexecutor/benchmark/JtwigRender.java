package io.hedwig.tcexecutor.benchmark;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

public class JtwigRender extends BaseBenchmark {

    private JtwigModel context;
    private JtwigTemplate template;
    private static final String templateName = "templates/jmeter_v.jmx";

    @Setup
    public void setup() {
         template = JtwigTemplate.classpathTemplate("templates/example.twig");
        context = JtwigModel.newModel().with("var", "World");

    }

    @Benchmark
    public String benchmark() {
        return template.render(context);

    }

}
