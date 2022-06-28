package app.sharding.service.impl;

import app.sharding.mapper.OrderMapper;
import app.sharding.po.Order;
import app.sharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Order Service 实现
 *
 * @author bruce.zhu@snowballtech.com
 * @since 2022-06-26 10:34:18
 */
@Service
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public int insert(Order order) {
        return orderMapper.insert(order);
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
