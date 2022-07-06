package demo.rpc.core.dto;

import lombok.Data;

/**
 * RpcRequest
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-03 10:31:22
 */
@Data
public class RpcRequest {

    /**
     * 接口类名称
     */
    private String serviceClass;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private Object[] argv;
}
