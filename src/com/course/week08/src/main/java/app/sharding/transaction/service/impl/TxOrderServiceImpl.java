package app.sharding.transaction.service.impl;

import app.sharding.mapper.OrderMapper;
import app.sharding.po.Order;
import app.sharding.transaction.exception.MyRollbackException;
import app.sharding.transaction.service.TxOrderService;
import org.apache.shardingsphere.transaction.annotation.ShardingSphereTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Order Service 实现 (带事务)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-26 10:34:18
 */
@Service
public class TxOrderServiceImpl implements TxOrderService {

    private OrderMapper orderMapper;

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional(rollbackFor = MyRollbackException.class)
    @ShardingSphereTransactionType(TransactionType.XA)
    public TransactionType insert(Order order) {
        orderMapper.insert(order);
        return TransactionTypeHolder.get();
    }

    @Override
    @Transactional(rollbackFor = MyRollbackException.class)
    @ShardingSphereTransactionType(TransactionType.XA)
    public void insertThenRollback(List<Order> orders) throws MyRollbackException {
        orderMapper.batchInsert(orders);
        throw new MyRollbackException("mock my rollback");
    }

    @Override
    public Order selectById(int id) {
        return orderMapper.selectById(id);
    }

    @Override
    public List<Order> listAllOrders() {
        return orderMapper.listAllOrders();
    }

    @Override
    public int countAllOrders() {
        return orderMapper.countAllOrders();
    }

    @Override
    public int deleteById(int id) {
        return orderMapper.deleteById(id);
    }
}
