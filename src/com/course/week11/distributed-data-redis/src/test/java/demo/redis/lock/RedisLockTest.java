package demo.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests for RedisLock
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-17 11:49:03
 */
@Slf4j
@SpringBootTest
public class RedisLockTest {

    private RedisLock redisLock;

    @Autowired
    public void setRedisLock(RedisLock redisLock) {
        this.redisLock = redisLock;
    }

    @Test
    public void lockAndRelease() throws InterruptedException {
        final boolean r = redisLock.tryLock(lockKey(), 10);
        log.info("try lock : {}, thread : {}", r, Thread.currentThread().getName());
        Thread t = anotherThread();
        t.start();
        Thread.sleep(3000);
        redisLock.releaseLock(lockKey());
        t.join();

        redisLock.tryLock(lockKey(), 10);
        t = anotherThread();
        t.start();
        Thread.sleep(3000);
        t.join();
        log.info("main thread end...");
    }

    private String lockKey() {
        return "Lock-A";
    }

    private Thread anotherThread() {
        return new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                log.info("another try lock started..., index : {}, thread : {}", i, Thread.currentThread().getName());
                if (redisLock.tryLock(lockKey(), 10)) {
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
