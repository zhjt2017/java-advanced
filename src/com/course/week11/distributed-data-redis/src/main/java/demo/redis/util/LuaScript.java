package demo.redis.util;

/**
 * utils of Lua Script
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-17 10:22:28
 */
public final class LuaScript {
    public static final String LUA_UNLOCK =
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
}
