package com.wdd.mail;

import com.rabbitmq.client.Channel;
import com.wdd.server.pojo.Employee;
import com.wdd.server.pojo.MailConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

/**
 * @author: wdd
 * @date: 2022/10/9 9:22
 */
@Component
public class MailReceiver {

    // 打印日志
    private static final Logger LOGGER= LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues = MailConstant.MAIL_QUEUE_NAME)
    public void handler(Message message, Channel channel){
        // 拿出员工
        Employee employee = (Employee) message.getPayload();
        MessageHeaders headers = message.getHeaders();
        // 消息号序列
        long tag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
        // 拿出msgId
        String msgId = (String) headers.get("spring_returned_message_correlation");

        HashOperations hashOperations = redisTemplate.opsForHash();
        try {

            if (hashOperations.entries("mail_log").containsKey(msgId)){
                LOGGER.error("消息已经被消费======={}",msgId);
                /**
                 * tag：消息序号
                 */
                channel.basicAck(tag,false);
                return;
            }
        // 创建消息
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        // 发件人
        helper.setFrom(mailProperties.getUsername());
        // 收件人
        helper.setTo(employee.getEmail());
        // 主题
        helper.setSubject("入职欢迎邮件");
        // 入职日期
        helper.setSentDate(new Date());
        // 邮件内容
        Context context = new Context();
        context.setVariable("name",employee.getName());
        context.setVariable("posName",employee.getPosition().getName());
        context.setVariable("joblevelName",employee.getJoblevel().getName());
        context.setVariable("politicsName",employee.getPoliticsStatus().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail,true);
            // 发送邮件
            javaMailSender.send(msg);
            LOGGER.info("邮件发送成功");
            // 更新到redis中
            hashOperations.put("mail_log",msgId,"ok");
            channel.basicAck(tag,false);
        } catch (MessagingException | IOException e) {
            try {
                channel.basicNack(tag,false,true);
            } catch (IOException ex) {
                LOGGER.error("邮件发送失败");
            }
            LOGGER.error("邮件发送失败");
        }


    }
}
