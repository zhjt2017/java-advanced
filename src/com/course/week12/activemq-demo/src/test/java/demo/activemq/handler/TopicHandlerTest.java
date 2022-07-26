package demo.activemq.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests for TopicHandler
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-24 05:54:11
 */
@SpringBootTest
public class TopicHandlerTest {

    private TopicHandler topicHandler;

    @Autowired
    public void setTopicHandler(TopicHandler topicHandler) {
        this.topicHandler = topicHandler;
    }

    @Test
    public void productAndConsume() throws InterruptedException {
        topicHandler.produce("topic-a", "Hello msg-01");
        topicHandler.produce("topic-a", "Hello msg-02");
        topicHandler.produce("topic-b", "Hello msg-03");
        topicHandler.produce("topic-a", "Hello msg-04");
        Thread.sleep(1000L);
    }
}
