package com.example.demo.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03 {

    /**
     * 解法如下：
     * 1、利用future.get()。
     * 2、自己构造一个实例，然后在主线程和子线程里利用
     * 该实例的wait(),notify()方法互通消息。（基础变量的封装类型会不生效，应该是没在堆上所致）
     * 3、思路和法二一样，只是实现是利用JUC的ReentrantLock和Condition的await()和signal()方法。
     * 4、利用引用传值和CountDownLatch控制主线程等待。
     * 5、利用CyclicBarrier
     * 6、利用Semaphore
     * 7、标识位+while(true)自旋
     * 8、LockSupport的park和unpark方法
     *
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException, BrokenBarrierException {
        
        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        /**
         * 法一
         */
        /**
         * 线程
         */
//        FutureTask<Integer> futureTask = new FutureTask<>(new SumTask());
//        Thread t = new Thread(futureTask, "Thread-01");
//        t.start();
//        //这是得到的返回值
//        Integer result = futureTask.get();


        /**
         * 线程池
         */
//        ExecutorService executorService = null;
//        Integer result = 0;
//        try {
//
//            executorService = Executors.newSingleThreadExecutor();
//            Future<Integer> future = executorService.submit(new SumTask());
//            result = future.get();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if (executorService != null) {
//                executorService.shutdown();
//            }
//        }


        /**
         * 法二
         */
//        Temp o = new Temp();
//        int result = 0;
//        SumTask1 myTask = new SumTask1();
//        myTask.setO(o);
//        FutureTask<Integer> futureTask = new FutureTask<>(myTask);
//        Thread t = new Thread(futureTask, "Thread-01");
//        t.start();
////
////        //这是得到的返回值
////        Integer result = futureTask.get();
//
//        synchronized (o) {
//            o.wait(0);
//            result = o.getI();
//        }

        /**
         * 法三
         */
//        Temp o = new Temp();
//        int result = 0;
//        ReentrantLock reentrantLock = new ReentrantLock();
//        Condition condition = reentrantLock.newCondition();
//        SumTask2 myTask = new SumTask2();
//        myTask.setReentrantLock(reentrantLock);
//        myTask.setCondition(condition);
//        myTask.setO(o);
//        FutureTask<Integer> futureTask = new FutureTask<>(myTask);
//        Thread t = new Thread(futureTask, "Thread-01");
//        t.start();
//
//        try {
//            reentrantLock.lock();
//            condition.await();
//            result = o.getI();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            reentrantLock.unlock();
//        }

        /**
         * 法四
         */

//        Temp o = new Temp();
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        SumTask3 myTask = new SumTask3();
//        myTask.setO(o);
//        myTask.setCountDownLatch(countDownLatch);
//        Thread t = new Thread(myTask, "Thread-01");
//        t.start();
//
//        countDownLatch.await();
//        //这是得到的返回值
//        Integer result = o.getI();


        /**
         * 法五
         */
//        Temp o = new Temp();
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
//        SumTask4 myTask = new SumTask4();
//        myTask.setO(o);
//        myTask.setCyclicBarrier(cyclicBarrier);
//        Thread t = new Thread(myTask, "Thread-01");
//        t.start();
//
//        cyclicBarrier.await();
//        //这是得到的返回值
//        Integer result = o.getI();

        /**
         * 法六
         */
//        Temp o = new Temp();
//        Semaphore semaphore = new Semaphore(1);
//        SumTask5 myTask = new SumTask5();
//        myTask.setO(o);
//        myTask.setSemaphore(semaphore);
//        new Thread(myTask).start();
//
//        //这里需要子线程先获取信号量，主线程等待1s
//        Thread.sleep(1000);
//        semaphore.acquire();
//        int result = o.getI();
//        semaphore.release();

        /**
         * 发七
         */
//        Temp o = new Temp();
//        SumTask6 myTask = new SumTask6();
//        myTask.setO(o);
//        new Thread(myTask).start();
//
//        while (!o.isFlag()) {
//            System.out.println("自旋等待子线程结束");
//        }
//
//        int result = o.getI();


        /**
         * 法八
         */
        Temp o = new Temp();
        SumTask7 myTask = new SumTask7();
        myTask.setO(o);
        myTask.setThread(Thread.currentThread());
        new Thread(myTask).start();

        LockSupport.park();
        int result = o.getI();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
         
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        
        // 然后退出main线程
    }
    
}
