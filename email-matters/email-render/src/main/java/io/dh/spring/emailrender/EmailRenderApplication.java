package io.dh.spring.emailrender;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.dh.spring.emailrender.dao.EmailConfigDao;
import io.dh.spring.emailrender.domain.EmailConfig;
import io.dh.spring.emailrender.service.EmailService;
import io.dh.spring.emailrender.service.VelocityRenderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration
public class EmailRenderApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EmailRenderApplication.class,args);
        EmailConfigDao dao = context.getBean(EmailConfigDao.class);

        Map<String,Object> result = dao.getMapById(2);

        for (Map.Entry<String, Object> entry : result.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        EmailConfig emailConfig = dao.getEmailConfigById(1);
        System.out.println(emailConfig);

        Map<String,Object> dataToRender = new HashMap<>();

        String colFieldMap = emailConfig.getColumnMapping();
        JSONObject map = JSON.parseObject(colFieldMap);
        List<Map<String,Object>> dbResult = dao.getResult(emailConfig.getContentSql());
        dataToRender.put("headers",dbResult.get(0).keySet());
        dataToRender.put("result",dbResult);

        VelocityRenderService renderService = context.getBean(VelocityRenderService.class);
        String html = renderService.renderToText("EMAIL_TEMPLATE_D.vm",dataToRender);
        System.out.println(html);

        EmailService emailService = context.getBean(EmailService.class);
        emailService.sendHtmlMail("abcds@test.com","abcds@test.com","subject",html);
        System.out.println("email send successfully!!");
    }
}
