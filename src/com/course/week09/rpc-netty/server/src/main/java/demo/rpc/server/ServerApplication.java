package demo.rpc.server;

import demo.rpc.core.netty.server.RpcNettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 不使用 spring boot web，启动 netty server 进行监听
 *
 * @author bruce.zhu@GeekTrainingCamp
 */
@SpringBootApplication
@Slf4j
public class ServerApplication implements ApplicationRunner {

    private final RpcNettyServer rpcNettyServer;

    public ServerApplication(RpcNettyServer rpcNettyServer) {
        this.rpcNettyServer = rpcNettyServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            rpcNettyServer.run();
        } catch (Exception e) {
            log.error("ServerApplication RpcNettyServer run error", e);
        } finally {
            rpcNettyServer.destroy();
        }
    }
}
