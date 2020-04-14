package io.qmeta.jpa.demos.generator.support;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

@Component
public class CodeTemplateRender {
    private Configuration freemarkerConfiguration;

    @PostConstruct
    public void initEngine() {
//        engine = TemplateUtil.createEngine(
//                new TemplateConfig("templates",
//                ResourceMode.COMPOSITE));
        freemarkerConfiguration =
                new Configuration(Configuration.VERSION_2_3_30);
        freemarkerConfiguration.setClassForTemplateLoading(this.getClass(),
                "/templates");
    }

    public String renderTemplate(Map data, String templateName) throws IOException, TemplateException {
        Template template = freemarkerConfiguration.getTemplate(templateName);
        StringWriter cout = new StringWriter();
        PrintWriter writer = new PrintWriter(cout);
        template.process(data, writer);
        writer.close();
        return cout.toString();
    }
}
