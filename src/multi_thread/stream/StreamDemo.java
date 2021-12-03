package multi_thread.stream;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        System.out.println(String.format("本计算机的核数：%d", Runtime.getRuntime().availableProcessors()));
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>(1000_00000);
        for (int i = 0; i < 1000_00000; i++) {
            list.add(random.nextInt(100));
        }
        long prevTime = getCurrentTime();
        list.stream().
                reduce((a, b) -> a + b).
                ifPresent(System.out::println);
        System.out.println(String.format("单线程计算耗时：%d", getCurrentTime() -prevTime));

        prevTime = getCurrentTime();
        list.stream().
                parallel().
                reduce((a, b) -> a + b).
                ifPresent(System.out::println);
        System.out.println(String.format("多线程计算耗时：%d", getCurrentTime() - prevTime));
    }
    private static long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
