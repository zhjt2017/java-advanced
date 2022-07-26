package demo.activemq.handler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests for QueueHandler
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-24 04:56:04
 */
@SpringBootTest
public class QueueHandlerTest {

    private QueueHandler queueHandler;

    @Autowired
    public void setQueueHandler(QueueHandler queueHandler) {
        this.queueHandler = queueHandler;
    }

    @Test
    public void produceAndConsume() throws InterruptedException {
        queueHandler.produce("Hello");
        queueHandler.produce("Hello, 1");
        queueHandler.produce("Hello, 2");
        Thread.sleep(200L);
    }
}
