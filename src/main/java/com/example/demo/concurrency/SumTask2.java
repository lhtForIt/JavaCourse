package com.example.demo.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lianght1
 * @date 2022/1/30
 */
public class SumTask2 implements Callable<Integer> {

    private ReentrantLock reentrantLock;
    private Condition condition;
    private Temp o;

    public Temp getO() {
        return o;
    }

    public void setO(Temp o) {
        this.o = o;
    }

    public ReentrantLock getReentrantLock() {
        return reentrantLock;
    }

    public void setReentrantLock(ReentrantLock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public Integer call() throws Exception {
        int r = 0;
        try {
            reentrantLock.lock();
            o.setI(fibo(10));
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
        return r;
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
