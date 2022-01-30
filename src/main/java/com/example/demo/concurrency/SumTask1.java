package com.example.demo.concurrency;

import java.util.concurrent.Callable;

/**
 * @author lianght1
 * @date 2022/1/30
 */
public class SumTask1 implements Callable<Integer> {
    private Temp o;

    public void setO(Temp o) {
        this.o = o;
    }

    @Override
    public Integer call() throws Exception {
        int r = 0;
        synchronized (o) {
            o.setI(fibo(10));
            o.notify();
        }
        return r;
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
