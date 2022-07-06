package demo.rpc.core.netty.util;

import lombok.Data;

/**
 * 自定义netty通信数据格式
 *
 * @author bruce.zhu@GeekTrainingCamp
 */
@Data
public class RpcProtocol {

    /**
     * 数据大小
     */
    private int len;

    /**
     * 数据内容
     */
    private byte[] content;
}
