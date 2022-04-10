package com.example.demo.mq.CustomMq.core;

import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Leo liang
 * @Date 2022/4/10
 *
 * 这是一个简单的数组队列实现，只有一些较简单的功能如offer,poll,poll(index)功能，
 * 如一些contaion和自动扩容功能暂未实现，且该队列为最简单的单向队列（不具备任何循环队列的特性）。
 *
 */
@Data
public class SimpleArrayQueue<T> {
    private Object[] items;
    private int size;//队列的容量
    private int top = -1;//队列尾部所在位置
    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public SimpleArrayQueue(int size) {
        items = new Object[size];
        this.size = size;
    }

    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.lock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    private void signalNotFull() {
        final ReentrantLock putLock = this.lock;
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    public boolean offer(T e) throws Exception {

        try {
            lock.lock();
            if (isFull()) {
                notFull.await();
//            throw new Exception("queue is full,can't add element");
            }

            items[++top] = e;

        }finally {
            lock.unlock();
        }

        signalNotEmpty();
        return true;
    }

    /**
     * 获取队列尾部元素
     */
    public T poll() throws Exception {
        T element = null;
        try {
            lock.lock();
            if (isEmpty()) {
                notEmpty.await();
//            throw new Exception("queue is empty,can't get element");
            }

            element = (T) items[top--];
        }finally {
            lock.unlock();
        }


        signalNotFull();
        return element;
    }


    public T poll(long timeout, TimeUnit milliseconds) throws Exception {
        if (isEmpty()) {
            notEmpty.await();
            throw new Exception("queue is empty,can't get element");
        }

        T element = (T) items[top--];
        signalNotFull();
        return element;
    }

    /**
     * 获取对应偏移量的元素
     */
    public T poll(int offset) throws Exception {
        if (isEmpty() || offset > top || offset < 0) {
            notEmpty.await();
//            throw new Exception("queue is empty/oversize top element,can't get element");
        }
        T element = (T) items[top--];

        signalNotFull();
        return element;
    }

    /**
     * 查看队尾元素
     */
    public T peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("queue is empty,can't get element");
        }
        T element = (T) items[top];
        return element;
    }



    public boolean isFull() { return top == size - 1;}
    public boolean isEmpty() { return top==-1;}




}
