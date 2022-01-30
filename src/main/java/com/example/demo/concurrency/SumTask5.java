package com.example.demo.concurrency;

import java.util.concurrent.Semaphore;

/**
 * @author lianght1
 * @date 2022/1/30
 */
public class SumTask5 implements Runnable {

    private Temp o;
    private Semaphore semaphore;

    public Temp getO() {
        return o;
    }

    public void setO(Temp o) {
        this.o = o;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            int i = fibo(10);
            o.setI(i);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
