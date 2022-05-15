package com.course.week02;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * GC日志演示
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-05-15 10:56:54
 */
public class GCLogAnalysis {

    private static Random RANDOM = new Random();

    public static void main(String[] args) {
        // 持续运行：1秒钟
        long seconds = 1;
        long endMillis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(seconds);

        LongAdder counter = new LongAdder();
        System.out.println("正在执行...");
        // 缓存一部分对象; 进入老年代
        int cacheSize = 2000;
        Object[] cachedGarbage = new Object[cacheSize];
        // 在此时间范围内,持续循环
        while (System.currentTimeMillis() < endMillis) {
            // 生成垃圾对象
            Object garbage = generateGarbage(100 * 1024);
            counter.increment();
            int randomIndex = RANDOM.nextInt(2 * cacheSize);
            if (randomIndex < cacheSize) {
                cachedGarbage[randomIndex] = garbage;
            }
        }
        System.out.println("执行结束!" + seconds + "秒内共生成对象次数:" + counter.longValue());
    }

    private static Object generateGarbage(int max) {
        int randomSize = RANDOM.nextInt(max);
        int type = randomSize % 4;
        switch (type) {
            case 0:
                return new int[randomSize];
            case 1:
                return new byte[randomSize];
            case 2:
                return new double[randomSize];
            default:
                StringBuilder builder = new StringBuilder();
                String randomString = "randomString-Anything";
                while (builder.length() < randomSize) {
                    builder.append(randomString);
                    builder.append(max);
                    builder.append(randomSize);
                }
                return builder.toString();
        }
    }
}
