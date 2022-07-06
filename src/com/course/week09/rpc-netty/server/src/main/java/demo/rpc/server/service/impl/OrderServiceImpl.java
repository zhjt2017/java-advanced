package demo.rpc.server.service.impl;

import demo.rpc.api.OrderService;
import demo.rpc.api.model.Order;

/**
 * @author bruce.zhu@GeekTrainingCamp
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findById(Integer id) {
        return new Order(1, "RPC", 1);
    }
}