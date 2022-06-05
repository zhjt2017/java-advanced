package mystarterusing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 * 特别说明：要想使用自定义starter，本地部署后需要执行命令到repository
 * <p>
 * mvn install:install-file -Dfile=jar路径 -DgroupId=待填 -DartifactId=待填 -Dversion=版本号 -Dpackaging=jar --settings=指定的settings文件
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-05 11:48:19
 */
@SpringBootApplication
public class MySpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }
}
