package com.example.demo.jvm01;

/**
 * @author Leo liang
 * @Date 2022/1/9
 */
public class Hello {
    public static void main(String[] args) {
        int a = 5, b = 6;

        for (int i = 0; i < 10; i++) {
            if (a == 5) {
                a = a + b;
            } else {
                b = a + a;
            }
        }

    }
}
