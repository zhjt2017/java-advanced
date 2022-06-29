package app.sharding.transaction.exception;

/**
 * 回滚时 自定义异常
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-26 10:34:18
 */
public class MyRollbackException extends Exception {

    public MyRollbackException() {
        super();
    }

    public MyRollbackException(String message) {
        super(message);
    }

    public MyRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyRollbackException(Throwable cause) {
        super(cause);
    }
}
