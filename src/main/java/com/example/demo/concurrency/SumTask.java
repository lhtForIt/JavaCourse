package com.example.demo.concurrency;

import sun.security.provider.Sun;

import java.util.concurrent.Callable;

/**
 * @author lianght1
 * @date 2022/1/30
 */
public class SumTask implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        return fibo(10);
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
