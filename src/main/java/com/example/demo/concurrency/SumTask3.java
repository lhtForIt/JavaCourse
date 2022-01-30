package com.example.demo.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author lianght1
 * @date 2022/1/30
 */
public class SumTask3 implements Runnable {

    private Temp o;
    private CountDownLatch countDownLatch;

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public Temp getO() {
        return o;
    }

    public void setO(Temp o) {
        this.o = o;
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    @Override
    public void run() {
        int i = fibo(10);
        o.setI(i);
        countDownLatch.countDown();
    }
}
