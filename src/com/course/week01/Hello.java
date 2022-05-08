package com.course.week01;

/**
 * Hello.java, 分析对应的字节码
 * (基本类型, 四则运算, if, for)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-24 11:36:24
 */
public class Hello {
    public static void main(String[] args) {
        final MovingAverage instance = new MovingAverage();
        int num1 = 1;
        int num2 = 2;
        instance.submit(num1);
        instance.submit(num2);
        double avg = instance.getAvg();

        if (avg > 0) {
            System.out.println("avg : " + avg);
        } else {
            System.out.println("invalid avg");
        }

        /**
         * javac -g -encoding utf-8 com\course\week01\Hello.java
         *
         * notepad++ 打开Hello.class文件与Hello$MovingAverage.class文件，16进制
         *
         * javap -c -verbose com.course.week01.Hello
         *
         */
    }

    static class MovingAverage {
        private int count = 0;
        private double sum = 0;

        public void submit(final double value) {
            this.count++;
            this.sum += value;
        }

        public double getAvg() {
            return 0 == this.count ? this.sum : this.sum / this.count;
        }
    }
}
