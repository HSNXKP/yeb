package com.wdd.server.config;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wdd.server.pojo.MailConstant;
import com.wdd.server.pojo.MailLog;
import com.wdd.server.service.MailLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




/**
 * @author: wdd
 * @date: 2022/10/12 10:47
 */
@Configuration
public class RabbitMQConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitTemplate.class);
    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private MailLogService mailLogService;

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate= new RabbitTemplate(connectionFactory);
        /**
         * 消息确认回调，确认消息是否到达broker
         * data：消息唯一标识
         * ack：确认结果
         * cause：失败原因
         */
        rabbitTemplate.setConfirmCallback(((data, ack, cause) -> {
            String msgId =data.getId();
            if (ack){
                LOGGER.info("{}=====>消息发送成功",msgId);
                mailLogService.update(new UpdateWrapper<MailLog>().set("status",1).eq("msgId",msgId));
            }else {
                LOGGER.error("{}=====>消息发送失败",msgId);
            }

        }));

        /**
         * 消息失败回调
         * msg:消息主题
         * repCode:状态码
         * repText:相应描述
         * exchange:交换机
         * routingKey:路由键
         */
        rabbitTemplate.setReturnCallback((msg,repCode,repText,exchange,routingKey)->{
            LOGGER.error("{}=====>消息发送失败",msg.getBody());
        });

        return rabbitTemplate;

    }

    /**
     * 队列
     * @return
     */
    @Bean
    public Queue queue(){
        return new Queue(MailConstant.MAIL_QUEUE_NAME);
    }

    /**
     * directExchange
     * direct类型交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MailConstant.MAIL_EXCHANGE_NAME);
    }

    /**
     * 绑定交换机 路由指定的队列
     * @return
     */
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(MailConstant.MAIL_ROUTING_KEY_NAME);
    }

}
