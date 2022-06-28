package app.sharding.service;

import app.sharding.po.Order;

import java.util.List;

/**
 * Order Service 定义
 *
 * @author bruce.zhu@snowballtech.com
 * @since 2022-06-26 10:34:18
 */
public interface OrderService {
    /**
     * 插入订单数据
     *
     * @param order
     * @return 插入几条记录
     */
    int insert(Order order);

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
