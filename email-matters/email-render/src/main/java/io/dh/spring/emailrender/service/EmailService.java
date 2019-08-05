package io.dh.spring.emailrender.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

    @Autowired
    public JavaMailSender javaMailSender;


    public void sendHtmlMail(String to, String from, String subject,
                             String msg) {

        try {
            MimeMessage mm = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mm);
            helper.setFrom(from);

            if (StringUtils.isEmpty(to)) throw new RuntimeException("请输入正确的邮件接收人");
            String[] recipients = to.split(";");
            InternetAddress[] addresses = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                addresses[i] = new InternetAddress(recipients[i]);
            }
            mm.setRecipients(Message.RecipientType.TO, addresses);
            mm.setSubject("this is subject "+subject);
            helper.setText(msg, true);
            javaMailSender.send(mm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
