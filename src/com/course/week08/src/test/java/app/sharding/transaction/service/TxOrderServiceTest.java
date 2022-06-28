package app.sharding.transaction.service;

import app.sharding.po.Order;
import app.sharding.transaction.exception.MyRollbackException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests for TxOrderService
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-26 11:53:27
 */
@Slf4j
@SpringBootTest
public class TxOrderServiceTest {

    private static final int SIZE = 21;

    private TxOrderService txOrderService;

    @Autowired
    public void setTxOrderService(TxOrderService txOrderService) {
        this.txOrderService = txOrderService;
    }

    @Test
    public void testInsert() {
        for (int i = 1; i <= 16; i++) {
            TransactionType transactionType = txOrderService.insert(buildOrder(i));
            txOrderService.deleteById(i);
            log.info("TransactionType: [{}]", transactionType);
        }
        log.info("end");
    }

    @Test
    public void testInsertThenRollback() {
        Order order1 = buildOrder(1);
        Order order2 = buildOrder(2);
        try {
            txOrderService.insertThenRollback(Arrays.asList(order1, order2));
        } catch (MyRollbackException e) {
            log.error("testInsertThenRollback error", e);
        }
        Integer count = txOrderService.countAllOrders();
        assertEquals(0, count);
    }

    @Test
    public void testSelectById() {
        for (int i = 1; i <= SIZE; i++) {
            Order order = txOrderService.selectById(i);
            assertNull(order);
        }
    }


    private Order buildOrder(int id) {
        final Order order = new Order();
        order.setId(id);
        order.setOrderNumber("B" + id);
        order.setUserId(2);
        order.setStatus((short) 1);
        order.setDeliverStatus((short) 0);
        order.setTotalPrice(200 + id);
        return order;
    }
}
