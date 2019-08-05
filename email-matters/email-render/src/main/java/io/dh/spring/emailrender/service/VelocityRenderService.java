package io.dh.spring.emailrender.service;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

@Component
public class VelocityRenderService {
    VelocityEngine velocityEngine;

    @PostConstruct
    public void init(){
        velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.setProperty("suffix",".vm");
        velocityEngine.setProperty("charset","utf-8");
        Properties p = new Properties();
        p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");

        velocityEngine.init(p);


    }
    public String renderToText(String template,Map<String,Object> data){

        Template t = velocityEngine.getTemplate(template);
        VelocityContext ctx = new VelocityContext();

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            ctx.put(entry.getKey(),entry.getValue());
        }
        StringWriter sw = new StringWriter();
        t.merge(ctx,sw);
        return sw.toString();
    }
}
