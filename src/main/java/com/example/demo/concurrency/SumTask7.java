package com.example.demo.concurrency;

import java.util.concurrent.locks.LockSupport;

/**
 * @author lianght1
 * @date 2022/1/30
 */
public class SumTask7 implements Runnable {
    private Temp o;
    private Thread thread;

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Temp getO() {
        return o;
    }

    public void setO(Temp o) {
        this.o = o;
    }

    @Override
    public void run() {
        o.setI(fibo(10));
        LockSupport.unpark(thread);
    }


    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
