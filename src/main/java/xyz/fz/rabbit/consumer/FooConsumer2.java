package xyz.fz.rabbit.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.fz.rabbit.configuration.RabbitmqConfiguration;
import xyz.fz.rabbit.model.FooMessage;

@Component
public class FooConsumer2 extends AbstractFooConsumer {

    @RabbitListener(queues = {RabbitmqConfiguration.QUEUE_FOO})
    public void fooConsume(FooMessage fooMessage, Message message, Channel channel) {
        this.defaultConsumer(fooMessage, message, channel);
    }
}
