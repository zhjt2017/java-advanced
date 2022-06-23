package demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot main
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @date 2022-06-22 06:27:39
 */
@Slf4j
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        log.info("============= Started successfully (Demo Application) =============");
    }

}
