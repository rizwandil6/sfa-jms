package adil.springframework.sfajms.Sender;

import adil.springframework.sfajms.Config.JmsConfig;
import adil.springframework.sfajms.Model.HelloWorldMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    public HelloSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage(){
        System.out.println("I'm Sending a message");

        HelloWorldMessage message = new HelloWorldMessage()
                    .builder()
                    .uuid(UUID.randomUUID())
                    .message("Salaam")
                    .build();
        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
        System.out.println("Message Sent!");

    }
}
