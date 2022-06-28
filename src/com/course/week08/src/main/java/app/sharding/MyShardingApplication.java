package app.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application of Db Sharding Demo
 *
 * @author bruce.zhu@snowballtech.com
 * @since 2022-06-26 10:34:18
 */
@SpringBootApplication
public class MyShardingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyShardingApplication.class, args);
    }
}
