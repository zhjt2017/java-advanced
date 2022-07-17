package demo.redis.inventory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Tests for InventoryService
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-17 11:03:03
 */
@Slf4j
@SpringBootTest
public class InventoryServiceTest {

    private InventoryService inventoryService;

    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    private Random r = new Random();

    @Test
    public void decrease() throws ExecutionException, InterruptedException {
        inventoryService.init(productId());
        final long balance = inventoryService.add(productId(), 100);
        log.info("InventoryService add 100...productId = {}, balance = {}", productId(), balance);

        final ExecutorService executorService = Executors.newFixedThreadPool(20);
        final List<Future<Integer>> f = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            f.add(executorService.submit(() -> {
                int amount = r.nextInt(15);
                boolean result = inventoryService.decrease(productId(), amount);
                log.info("InventoryService decrease result : {}, productId = {}, amount = {}, thread = {}",
                        result, productId(), amount, Thread.currentThread().getName());
                return amount;
            }));
        }

        int totalDecrease = 0;
        for (Future<Integer> future : f) {
            totalDecrease += future.get();
        }
        log.info("InventoryServiceTest test decrease end...totalDecrease = {}", totalDecrease);
    }

    private String productId() {
        return "A1";
    }
}
