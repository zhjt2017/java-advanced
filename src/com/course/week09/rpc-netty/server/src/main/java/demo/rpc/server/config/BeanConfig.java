package demo.rpc.server.config;

import demo.rpc.api.OrderService;
import demo.rpc.api.UserService;
import demo.rpc.server.service.impl.OrderServiceImpl;
import demo.rpc.server.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bruce.zhu@GeekTrainingCamp
 */
@Configuration
public class BeanConfig {

    @Bean("demo.rpc.api.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean("demo.rpc.api.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
