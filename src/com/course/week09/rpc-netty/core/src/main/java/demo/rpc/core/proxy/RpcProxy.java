package demo.rpc.core.proxy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bruce.zhu@GeekTrainingCamp
 */
class RpcProxy {

    private ConcurrentHashMap<String, Object> proxyCache = new ConcurrentHashMap<>();

    Object getProxy(String className) {
        return proxyCache.get(className);
    }

    Boolean isExit(String className) {
        return proxyCache.containsKey(className);
    }

    void add(String className, Object proxy) {
        proxyCache.put(className, proxy);
    }
}