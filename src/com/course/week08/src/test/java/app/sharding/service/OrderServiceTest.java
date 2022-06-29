package app.sharding.service;

import app.sharding.po.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for OrderService
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-26 10:34:18
 */
@SpringBootTest
public class OrderServiceTest {

    private static final int SIZE = 20;

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Test
    public void testInsert() {
        for (int i = 1; i <= SIZE; i++) {
            System.out.println(i + " insert, " + orderService.insert(buildOrder(i)));
        }
    }

    @Test
    public void testSelectById() {
        for (int i = 1; i <= SIZE; i++) {
            Order order = orderService.selectById(i);
            assertNotNull(order);
            assertEquals(i, order.getId());
        }
    }

    @Test
    public void testListAllOrders() {
        List<Order> orders = orderService.listAllOrders();
        assertNotNull(orders);
        assertEquals(SIZE, orders.size());
    }

    @Test
    public void testCountAllOrders() {
        Integer count = orderService.countAllOrders();
        assertEquals(SIZE, count);
    }

    @Test
    public void testDeleteById() {
        for (int i = 1; i <= SIZE; i++) {
            System.out.println(i + " delete, " + orderService.deleteById(i));
        }
    }

    private Order buildOrder(int id) {
        final Order order = new Order();
        order.setId(id);
        order.setOrderNumber("A" + id);
        order.setUserId(2);
        order.setStatus((short) 1);
        order.setDeliverStatus((short) 0);
        order.setTotalPrice(100 + id);
        return order;
    }
}
