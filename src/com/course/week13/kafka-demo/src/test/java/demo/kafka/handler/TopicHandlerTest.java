package demo.kafka.handler;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests for TopicHandler
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-30 03:40:42
 */
@Slf4j
@SpringBootTest
public class TopicHandlerTest {

    private TopicHandler topicHandler;

    @Autowired
    public void setTopicHandler(TopicHandler topicHandler) {
        this.topicHandler = topicHandler;
    }

    @Test
    public void sendAndReceive() {
        final int count = 1000;
        final long start = System.currentTimeMillis();

        for (int i = 1; i <= count; i++) {
            topicHandler.sendMessage("msg-" + i);
        }

        final long sendingEnd = System.currentTimeMillis();
        log.info("count {}, sending end time {}, taking {} millis...", count, sendingEnd, (sendingEnd - start));
    }
}
