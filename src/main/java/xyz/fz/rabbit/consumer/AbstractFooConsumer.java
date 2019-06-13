package xyz.fz.rabbit.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import xyz.fz.rabbit.configuration.RabbitmqConfiguration;
import xyz.fz.rabbit.model.FooMessage;

import javax.annotation.Resource;

public abstract class AbstractFooConsumer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    void defaultConsumer(FooMessage fooMessage, Message message, Channel channel) {
        try {
            System.out.println(fooMessage);
        } catch (Exception e) {
            rabbitTemplate.convertAndSend(RabbitmqConfiguration.EXCHANGE_FOO_ERR, RabbitmqConfiguration.ROUTING_FOO_ERR, fooMessage);
        } finally {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
