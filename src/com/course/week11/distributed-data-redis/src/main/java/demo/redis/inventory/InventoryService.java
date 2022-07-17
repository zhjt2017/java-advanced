package demo.redis.inventory;

import demo.redis.util.RedisCounterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Inventory Service
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-17 10:45:56
 */
@Slf4j
@Component
public class InventoryService {

    private RedisCounterUtil redisCounterUtil;

    @Autowired
    public void setRedisCounterUtil(RedisCounterUtil redisCounterUtil) {
        this.redisCounterUtil = redisCounterUtil;
    }

    /**
     * 对一种商品初始化库存 (不管之前有没有该redis key, 初始化为0, 归零)
     *
     * @param productId
     */
    public void init(final String productId) {
        redisCounterUtil.init(buildRedisKey(productId));
    }

    /**
     * 对一种商品增加库存 (进货)
     *
     * @param productId
     * @param amount
     * @return
     */
    public long add(final String productId, final long amount) {
        final String redisKey = buildRedisKey(productId);
        return redisCounterUtil.increment(redisKey, amount);
    }

    /**
     * 用户消费，扣减库存
     *
     * @param productId
     * @param amount
     * @return
     */
    public boolean decrease(final String productId, final long amount) {
        final String redisKey = buildRedisKey(productId);
        final long balance = redisCounterUtil.decrement(redisKey, amount);
        log.debug("counter before decrease : {}, thread : {}", balance + amount, Thread.currentThread().getName());
        if (balance >= 0) {
            return true;
        }
        redisCounterUtil.increment(redisKey, amount);
        return false;
    }

    private static final String REDIS_KEY_PREFIX = "Counter:Inventory:";

    private String buildRedisKey(final String productId) {
        return REDIS_KEY_PREFIX + productId;
    }
}
