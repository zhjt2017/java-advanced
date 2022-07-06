package demo.rpc.core.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * RpcRequest
 *
 * @author bruce.zhu@GeekTrainingCamp
 */
@Slf4j
@Getter
public class ServiceException extends RuntimeException {

    private String msg;

    public ServiceException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
