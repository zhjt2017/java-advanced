package demo.redis.lock;

import demo.redis.util.LuaScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Distributed Lock (Base Redis)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-17 11:38:29
 */
@Component
public class RedisLock {

    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final byte[] KEY_LOCK_REQUEST_ID = "Lock".getBytes(StandardCharsets.UTF_8);

    /**
     * redis分布式锁 - 加锁 (必须设置expireSeconds, 以防止死锁)
     *
     * @param lockKey
     * @param expireSeconds
     * @return
     */
    public boolean tryLock(final String lockKey, final long expireSeconds) {
        RedisCallback<Boolean> callback = (connection) ->
                connection.set(lockKey.getBytes(StandardCharsets.UTF_8), KEY_LOCK_REQUEST_ID,
                        Expiration.seconds(expireSeconds), RedisStringCommands.SetOption.SET_IF_ABSENT);
        return this.redisTemplate.execute(callback);
    }

    /**
     * redis分布式锁 - (主动)释放锁
     *
     * @param lockKey
     * @return
     */
    public boolean releaseLock(final String lockKey) {
        RedisCallback<Boolean> callback = (connection) -> {
            return connection.eval(LuaScript.LUA_UNLOCK.getBytes(), ReturnType.BOOLEAN, 1,
                    lockKey.getBytes(StandardCharsets.UTF_8), KEY_LOCK_REQUEST_ID);
        };
        return this.redisTemplate.execute(callback);
    }
}
