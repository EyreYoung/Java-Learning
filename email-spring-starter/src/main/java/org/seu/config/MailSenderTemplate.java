package org.seu.config;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.seu.model.MailDto;
import org.seu.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 邮件发送模版类
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "boot.email.enable", havingValue = "true")
public class MailSenderTemplate {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String from;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    public Result send(MailDto mailDto) {
        if (StringUtils.isAnyBlank(mailDto.getTo(), mailDto.getContent())) {
            return new Result(false, 1001, "接收人或邮件内容不能为空");
        }

        String[] tos = filterEmail(mailDto.getTo().split(","));
        if (tos == null) {
            log.error("邮件发送失败，接收人邮箱格式不正确：{}", mailDto.getTo());
            return new Result(false, 1002, "");
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(tos);
            helper.setText(mailDto.getContent(), true);
            helper.setSubject(mailDto.getSubject());

            //抄送
            String[] ccs = filterEmail(mailDto.getCc().split(","));
            if (ccs != null) {
                helper.setCc(ccs);
            }

            //秘抄
            String[] bccs = filterEmail(mailDto.getBcc().split(","));
            if (bccs != null) {
                helper.setBcc(bccs);
            }

            //定时发送
            if (mailDto.getSendDate() != null) {
                helper.setSentDate(mailDto.getSendDate());
            }

            //附件
            File[] files = mailDto.getFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    helper.addAttachment(file.getName(), file);
                }
            }
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("邮件发送异常：{}", e.getMessage(), e);
            return new Result(false, 1099, "邮件发送异常：" + e.getMessage());
        }
        return new Result();
    }

    public Result send(String to, String content, String subject) {
        return send(MailDto.builder().to(to).content(content).subject(subject).build());
    }
    public Result send(String to, String content, String subject, String cc) {
        return send(MailDto.builder().to(to).content(content).subject(subject).cc(cc).build());
    }

    public Result sendTemplate(String to, Map<String, Object> model, String template, String subject) {
        return send(MailDto.builder().to(to).content(getTemplateStr(model, template)).subject(subject).build());
    }

    public Result sendTemplate(String to, Map<String, Object> model, String template, String subject, String cc) {
        return send(MailDto.builder().to(to).content(getTemplateStr(model, template)).subject(subject).cc(cc).build());
    }

    private String getTemplateStr(Map<String, Object> model, String template) {
        try {
            return FreeMarkerTemplateUtils.processTemplateIntoString(
                    freeMarkerConfigurer.getConfiguration().getTemplate(template),
                    model);
        } catch (Exception e) {
            log.error("获取模版数据异常: {}", e.getMessage(), e);
        }
        return "";
    }

    private String[] filterEmail(String[] emails) {
        List<String> list = Arrays.asList(emails);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        list = list.stream().filter(this::checkEmail).collect(Collectors.toList());
        return list.toArray(new String[0]);
    }

    private boolean checkEmail(String email) {
        return REUtil.matches("\\\\w+@\\\\w+\\\\.[a-z]+(\\\\.[a-z]+)?", email);
    }

}
