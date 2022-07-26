package demo.activemq.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Topic;
import java.util.HashMap;
import java.util.List;

/**
 * ActiveMQ produce and consume by Topic
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-24 03:34:42
 */
@Slf4j
@Component
public class TopicHandler {

    private JmsTemplate jmsTemplate;
    private HashMap<String, Topic> topics;

    @Autowired
    @Qualifier("topicTemplate")
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Autowired
    public void setTopics(List<Topic> topicList) throws JMSException {
        topics = new HashMap<>(topicList.size());
        for (Topic t : topicList) {
            topics.put(t.getTopicName(), t);
        }
        System.out.println("topics : " + topics);
    }

    public void produce(final String topicName, final String msg) {
        jmsTemplate.convertAndSend(topic(topicName), msg);
        log.info("Topic produce msg success : {}, topicName : {}", msg, topicName);
    }

    /**
     * destination用的是topic name而不是Topic对象的名称
     *
     * @param msg
     */
    @JmsListener(destination = "topic-a", containerFactory = "topicListener")
    public void consumeTopicA(final String msg) {
        log.info("Topic consume msg success : {}, topicName : {}", msg, "topic-a");
    }

    @JmsListener(destination = "topic-b", containerFactory = "topicListener")
    public void consumeTopicB(final String msg) {
        log.info("Topic consume msg success : {}, topicName : {}", msg, "topic-b");
    }

    private Topic topic(final String topicName) {
        return topics.get(topicName);
    }
}
