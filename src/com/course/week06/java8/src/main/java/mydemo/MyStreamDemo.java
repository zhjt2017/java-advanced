package mydemo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * java8 Stream Demo
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-16 11:50:31
 */
public class MyStreamDemo {
    public static void main(String[] args) {
        int sum = IntStream.of(1, 2, 3, 4).sum();
        // IntStream内部就是reduce(0, Integer::sum)的实现
        System.out.println("IntStream sum : " + sum);
        sum = Arrays.asList(1, 2, 3, 4).stream().reduce(0, Integer::sum);
        System.out.println("Stream reduce (FunctionalInterface) sum : " + sum);
        sum = Arrays.asList(1, 2, 3, 4).stream().reduce(0, (a, b) -> a + b);
        System.out.println("Stream reduce (Lambda) sum : " + sum);

        int multi = IntStream.of(1, 2, 3, 4).reduce(1, (a, b) -> a * b);
        System.out.println("IntStream multi : " + multi);

        Map<Integer, String> map = Arrays.asList(1, 2, 3, 4).stream().parallel().collect(Collectors
                .toMap(a -> a, a -> "S-" + a, (a, b) -> a, HashMap::new));
        System.out.println("Stream collect toMap : " + map);

        map.forEach((k, v) -> System.out.println("key : " + k + ", value : " + v));
        List<String> list = map.entrySet().parallelStream().map(e -> "key : " + e.getKey() + ", value : " + e.getValue()).collect(Collectors.toList());
        System.out.println("list : " + list);
    }
}
