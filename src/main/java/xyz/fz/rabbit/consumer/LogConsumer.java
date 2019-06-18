package xyz.fz.rabbit.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xyz.fz.rabbit.configuration.RabbitmqConfiguration;

@Component
public class LogConsumer {
    @RabbitListener(queues = {RabbitmqConfiguration.QUEUE_LOG})
    public void log(String log, Message message, Channel channel) {
        try {
            System.out.print(log);
        } finally {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
