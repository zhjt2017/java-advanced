package app.sharding.transaction.service;

import app.sharding.po.Order;
import app.sharding.transaction.exception.MyRollbackException;
import org.apache.shardingsphere.transaction.core.TransactionType;

import java.util.List;

/**
 * Order Service 定义 (带事务)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-26 10:34:18
 */
public interface TxOrderService {
    /**
     * 插入订单数据
     *
     * @param order
     * @return 插入几条记录
     */
    TransactionType insert(Order order);

    /**
     * 测试分布式事务
     *
     * @param orders
     * @throws MyRollbackException
     */
    void insertThenRollback(List<Order> orders) throws MyRollbackException;

    /**
     * 查询订单
     *
     * @param id
     * @return 订单数据
     */
    Order selectById(int id);

    /**
     * 查询全部订单 (要有条件，不然太多)
     *
     * @return 订单列表
     */
    List<Order> listAllOrders();

    /**
     * 查询全部订单数量
     *
     * @return 订单数量
     */
    int countAllOrders();

    /**
     * 根据主键删除订单
     *
     * @param id
     * @return 订单数量
     */
    int deleteById(int id);
}
