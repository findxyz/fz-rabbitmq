package xyz.fz.rabbit.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfiguration {
    public static final String EXCHANGE_FOO = "exchange.foo";

    public static final String QUEUE_FOO = "queue.foo";

    public static final String ROUTING_FOO = "routing.foo";

    public static final String EXCHANGE_FOO_ERR = "exchange.foo.err";

    public static final String QUEUE_FOO_ERR = "queue.foo.err";

    public static final String ROUTING_FOO_ERR = "routing.foo.err";

    @Bean
    public TopicExchange fooExchange() {
        return new TopicExchange(EXCHANGE_FOO);
    }

    @Bean
    public Queue fooQueue() {
        return new Queue(QUEUE_FOO);
    }

    @Bean
    public Binding fooBinding(@Qualifier("fooQueue") Queue fooQueue, @Qualifier("fooExchange") TopicExchange fooExchange) {
        return BindingBuilder.bind(fooQueue).to(fooExchange).with(ROUTING_FOO);
    }

    @Bean
    public TopicExchange fooErrExchange() {
        return new TopicExchange(EXCHANGE_FOO_ERR);
    }

    @Bean
    public Queue fooErrQueue() {
        return new Queue(QUEUE_FOO_ERR);
    }

    @Bean
    public Binding fooErrBinding(@Qualifier("fooErrQueue") Queue fooErrQueue, @Qualifier("fooErrExchange") TopicExchange fooErrExchange) {
        return BindingBuilder.bind(fooErrQueue).to(fooErrExchange).with(ROUTING_FOO_ERR);
    }
}
