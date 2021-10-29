package com.fangming.rabbitmqdemo.rabbitmq;

import com.fangming.rabbitmqdemo.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 作者：LSH
 * 日期：2019/12/18 21:44
 * 生产者 生产消息
 * @author DELL
 */
@Component
@Slf4j
public class DelaySender {

    private final AmqpTemplate amqpTemplate;

    public DelaySender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendDelay(Order order) {
        //this.amqpTemplate.convertAndSend的第一个参数为延迟交换机的名称，第二个为延时消费routing-key，第三个参数为order操作对象，第四个参数为消息
        log.info("【订单生成时间】" + new Date().toString() +"【1分钟后检查订单是否已经支付】" + order.toString() );
        this.amqpTemplate.convertAndSend(DelayRabbitConfig.ORDER_DELAY_EXCHANGE, DelayRabbitConfig.ORDER_DELAY_ROUTING_KEY, order, message -> {
            // 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(1000 * 6 + "");
            return message;
        });
    }
}

