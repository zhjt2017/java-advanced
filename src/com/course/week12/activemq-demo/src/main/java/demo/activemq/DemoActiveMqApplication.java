package demo.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * Application Starter of demo - activemq
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-07-24 02:40:42
 */
@EnableJms
@SpringBootApplication
public class DemoActiveMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoActiveMqApplication.class, args);
    }
}
