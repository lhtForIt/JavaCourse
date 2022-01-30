package com.example.demo.concurrency;

/**
 * @author lianght1
 * @date 2022/1/30
 */
public class SumTask6 implements Runnable{

    private Temp o;
    private String flag;

    public Temp getO() {
        return o;
    }

    public void setO(Temp o) {
        this.o = o;
    }

    @Override
    public void run() {
        o.setI(fibo(10));
        o.setFlag(true);
    }


    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
