package demo.activemq.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * ActiveMQ produce and consume by Queue
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-24 03:34:42
 */
@Slf4j
@Component
public class QueueHandler {

    private JmsTemplate jmsTemplate;

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    private static final String QUEUE_NAME = "demo.queue";

    public void produce(final String msg) {
        jmsTemplate.convertAndSend(QUEUE_NAME, msg);
        log.info("Queue produce msg success : {}", msg);
    }

    @JmsListener(destination = QUEUE_NAME)
    public void consume(final String msg) {
        log.info("Queue consume msg success : {}, queue : {}", msg, QUEUE_NAME);
    }
}
