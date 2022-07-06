package demo.rpc.client;

import demo.rpc.api.OrderService;
import demo.rpc.api.UserService;
import demo.rpc.api.model.Order;
import demo.rpc.api.model.User;
import demo.rpc.core.proxy.RpcByteBuddy;
import demo.rpc.core.proxy.RpcClient;
import demo.rpc.core.proxy.RpcClientJdk;
import lombok.extern.slf4j.Slf4j;

/**
 * @author bruce.zhu@GeekTrainingCamp
 */
@Slf4j
public class ClientApplication {

    public static void main(String[] args) {

        RpcClient jdk = new RpcClientJdk();
        UserService userService = jdk.create(UserService.class, "http://localhost:8080/");
        User user = userService.findById(1);
        if (user == null) {
            log.info("Clint service invoke Error");
            return;
        }
        log.info("find user from server: {}, id = {}", user.getName(), 1);

        RpcClient buddy = new RpcByteBuddy();
        OrderService orderService = buddy.create(OrderService.class, "http://localhost:8080/");
        Order order = orderService.findById(1992129);
        if (order == null) {
            log.info("Clint service invoke Error");
            return;
        }
        log.info("find order, name = {}, userId = {}", order.getName(), order.getUserId());

    }
}
