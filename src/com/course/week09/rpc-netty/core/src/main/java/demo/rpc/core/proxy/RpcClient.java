package demo.rpc.core.proxy;

/**
 * @author bruce.zhu@GeekTrainingCamp
 */
public interface RpcClient {

    /**
     * create proxy
     *
     * @param serviceClass
     * @param url          server url
     * @param <T>          T
     * @return proxy class
     */
    <T> T create(final Class<T> serviceClass, final String url);
}
