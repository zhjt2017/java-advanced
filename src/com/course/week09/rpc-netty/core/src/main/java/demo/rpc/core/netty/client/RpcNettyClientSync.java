package demo.rpc.core.netty.client;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import demo.rpc.core.dto.RpcRequest;
import demo.rpc.core.dto.RpcResponse;
import demo.rpc.core.netty.util.RpcProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author bruce.zhu@GeekTrainingCamp
 */
@Slf4j
public class RpcNettyClientSync {

    private enum EnumSingleton {
        /**
         * 懒汉枚举单例
         */
        INSTANCE;
        private RpcNettyClientSync instance;

        EnumSingleton() {
            instance = new RpcNettyClientSync();
        }

        public RpcNettyClientSync getSingleton() {
            return instance;
        }
    }

    public static RpcNettyClientSync getInstance() {
        return EnumSingleton.INSTANCE.getSingleton();
    }

    /**
     * 使用Map来保存用过的Channel，看下次相同的后台服务是否能够重用，起一个类似缓存的作用
     */
    private ConcurrentHashMap<String, Channel> channelPool = new ConcurrentHashMap<>();
    private EventLoopGroup clientGroup = new NioEventLoopGroup(new ThreadFactoryBuilder().setNameFormat("client work-%d").build());

    private RpcNettyClientSync() {
    }

    /**
     * 调用channel发送请求，从handler中获取响应结果
     *
     * @return 响应
     * @throws InterruptedException exception
     */
    public RpcResponse getResponse(RpcRequest rpcRequest, String url) throws InterruptedException,
            URISyntaxException {
        RpcProtocol request = convertNettyRequest(rpcRequest);

        URI uri = new URI(url);
        String cacheKey = uri.getHost() + ":" + uri.getPort();

        // 查看缓存池中是否有可重用的channel
        if (channelPool.containsKey(cacheKey)) {
            Channel channel = channelPool.get(cacheKey);
            if (!channel.isActive() || !channel.isWritable() || !channel.isOpen()) {
                log.debug("Channel can't reuse");
            } else {
                try {
                    RpcClientSyncHandler handler = new RpcClientSyncHandler();
                    handler.setLatch(new CountDownLatch(1));
                    channel.pipeline().replace("clientHandler", "clientHandler", handler);
                    channel.writeAndFlush(request).sync();
                    return handler.getResponse();
                } catch (Exception e) {
                    log.debug("channel reuse send msg failed!");
                    channel.close();
                    channelPool.remove(cacheKey);
                }
                log.debug("Handler is busy, please user new channel");
            }
        }

        // 没有或者不可用则新建
        // 并将最终的handler添加到pipeline中，拿到结果后返回
        RpcClientSyncHandler handler = new RpcClientSyncHandler();
        handler.setLatch(new CountDownLatch(1));

        Channel channel = createChannel(uri.getHost(), uri.getPort());
        channel.pipeline().replace("clientHandler", "clientHandler", handler);
        channelPool.put(cacheKey, channel);

        channel.writeAndFlush(request).sync();
        return handler.getResponse();
    }

    /**
     * 返回新的Channel
     *
     * @param address ip地址
     * @param port    端口
     * @return channel
     * @throws InterruptedException exception
     */
    private Channel createChannel(String address, int port) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.AUTO_CLOSE, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioSocketChannel.class)
                .handler(new RpcClientInitializer());
        return bootstrap.connect(address, port).sync().channel();
    }

    /**
     * 将 {@RpcRequest} 转成 netty 自定义的通信格式 {@RpcProtocol}
     *
     * @param rpcRequest RpcRequest
     * @return RpcProtocol
     */
    private RpcProtocol convertNettyRequest(RpcRequest rpcRequest) {
        RpcProtocol request = new RpcProtocol();
        String requestJson = JSON.toJSONString(rpcRequest);
        request.setLen(requestJson.getBytes(CharsetUtil.UTF_8).length);
        request.setContent(requestJson.getBytes(CharsetUtil.UTF_8));
        return request;
    }

    /**
     * 关闭线程池
     */
    public void destroy() {
        clientGroup.shutdownGracefully();
    }
}