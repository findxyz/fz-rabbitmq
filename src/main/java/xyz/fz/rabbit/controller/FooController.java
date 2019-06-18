package xyz.fz.rabbit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fz.rabbit.configuration.RabbitmqConfiguration;
import xyz.fz.rabbit.model.FooMessage;

import javax.annotation.Resource;

@RestController
public class FooController {
    private static Logger LOGGER = LoggerFactory.getLogger(FooController.class);

    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/foo/{message}")
    public String fooMessage(@PathVariable("message") String message) {
        FooMessage fooMessage = new FooMessage();
        fooMessage.setId(System.currentTimeMillis() + "");
        fooMessage.setContent(message);
        rabbitTemplate.convertAndSend(RabbitmqConfiguration.EXCHANGE_FOO, RabbitmqConfiguration.ROUTING_FOO, fooMessage);
        LOGGER.info("Hello Rabbitmq!");
        return "ok";
    }
}
