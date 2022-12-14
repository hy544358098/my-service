package com.crc.common.utils.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

/**
 * 邮箱服务
 *
 * @author xiaoming
 * @since 2021-09-20
 */
@Configuration
@Slf4j
public class EmailService {
    @Resource
    JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String from;

    /**
     * @param sendToList 主要接收人
     * @param copyList   抄送人员列表
     * @param subject    主题
     * @param body       内容 @Return
     * @Description 发送邮件，失败重试一次
     * @Date 2021/11/5 10:45
     */
    @Retryable(
            value = RetryException.class,
            maxAttempts = 2,
            backoff = @Backoff(delay = 3000, multiplier = 1.5))
    public void sendEmail(List<String> sendToList, List<String> copyList, String subject, String body) {
        log.info("Sending!");
        try {
            //创建SimpleMailMessage对象
            SimpleMailMessage message = new SimpleMailMessage();
            //邮件发送人
            message.setFrom(from);
            if (CollectionUtils.isEmpty(sendToList)){
                log.error("sendToList is empty:{}", sendToList);
                return;
            }
            //邮件接收人
            message.setTo(sendToList.stream().toArray(String[]::new));
            //邮件抄送人
            message.setCc(CollectionUtils.isEmpty(copyList) ? null : copyList.stream().toArray(String[]::new));
            //邮件主题
            message.setSubject(subject);
            //邮件内容
            message.setText(body);
            //发送邮件
            mailSender.send(message);
            log.info("send email success!");
        } catch (MailException e) {
            log.error("send email failed:{}", e);
            throw new RetryException("send email failed");
        }
    }

    @Recover
    public void recover(RetryException e) {
        log.info("retry send email success!");
    }

    /**
     * 简单文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleMail(String to, String subject, String content) {
        //创建SimpleMailMessage对象
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送人
        message.setFrom(from);
        //邮件接收人
        message.setTo(to);
        //邮件主题
        message.setSubject(subject);
        //邮件内容
        message.setText(content);
        //发送邮件
        mailSender.send(message);
    }

    /**
     * html邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlMail(String to, String subject, String content) {
        //获取MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(from);
            //邮件接收人
            messageHelper.setTo(subject);
            //邮件主题
            message.setSubject(subject);
            //邮件内容，html格式
            messageHelper.setText(content, true);
            //发送
            mailSender.send(message);
            //日志信息
            log.info("邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常！", e);
        }
    }

    /**
     * 带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            //日志信息
            log.info("邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常！", e);
        }
    }
}
