package demo.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis operation utils for counter
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-17 10:28:57
 */
@Component
public class RedisCounterUtil {

    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long decrement(final String redisKey, final long count) {
        return redisTemplate.opsForValue().decrement(redisKey, count);
    }

    public long increment(final String redisKey, final long count) {
        return redisTemplate.opsForValue().increment(redisKey, count);
    }

    public void init(final String redisKey) {
        redisTemplate.opsForValue().set(redisKey, String.valueOf(0));
    }
}
