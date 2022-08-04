package demo.kafka.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * kafka produce and consume by Topic
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-30 03:34:42
 */
@Slf4j
@Component
public class TopicHandler {

    private static final String TOPIC_NAME = "kafka.topic-A";

    private KafkaTemplate<Object, Object> template;

    @Autowired
    public void setTemplate(KafkaTemplate<Object, Object> template) {
        this.template = template;
    }

    public void sendMessage(String message) {
        this.template.send(TOPIC_NAME, message);
    }

    @KafkaListener(topics = TOPIC_NAME)
    public void receiveMessage(String message) {
        log.info("KafkaListener receive message : {}", message);
    }
}
