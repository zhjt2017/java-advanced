package demo.rpc.core.netty.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义RPC编码器
 *
 * @author bruce.zhu@GeekTrainingCamp
 */
@Slf4j
public class RpcEncoder extends MessageToByteEncoder<RpcProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcProtocol msg, ByteBuf out) throws Exception {
        log.info("Netty rpc encode run");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
    }
}