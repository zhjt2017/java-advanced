import java.util.concurrent.*;

/**
 * 在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-05-29 06:41:55
 */
public class MyThreadReturn2Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Main Thread : " + Thread.currentThread().getName());
        System.out.println("System Processors : " + Runtime.getRuntime().availableProcessors());
        // 方式一：线程池submit
        long start = System.currentTimeMillis();
        ExecutorService threadPool = new ThreadPoolExecutor(1, 1, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));
        System.out.println("Create Thread Pool, taking milliseconds : " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        Future<Integer> f = threadPool.submit(new FiboCallable());
        System.out.println("Thread Pool submit, taking milliseconds : " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        System.out.println("return value (thread pool submit) : " + f.get() + ", taking milliseconds : " + (System.currentTimeMillis() - start));
        threadPool.shutdown();

        // 方式二：FutureTask
        start = System.currentTimeMillis();
        FutureTask<Integer> futureTask = new FutureTask<>(new FiboCallable());
        new Thread((futureTask)).start();
        System.out.println("return value (FutureTask) : " + futureTask.get() + ", taking milliseconds : " + (System.currentTimeMillis() - start));

        // 方式三：join等待到执行线程执行结束，自己定义共享变量作为返回值，我认为这个现在不推荐了，使用Future替代

        // 方式四：CompletableFuture执行线程执行完后立即回调主线程 (主线程作为消费者，执行线程作为生产者)
        start = System.currentTimeMillis();
        Integer result = CompletableFuture.supplyAsync(MyThreadReturn2Main::sum).thenApplyAsync((a) -> (a)).join();
        System.out.println("return value (CompletableFuture supplyAsync, thenApplyAsync) : " + futureTask.get() + ", taking milliseconds : " + (System.currentTimeMillis() - start));
    }

    static class FiboCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return MyThreadReturn2Main.sum();
        }
    }

    private static int sum() {
        System.out.println("sum Thread : " + Thread.currentThread().getName());
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
