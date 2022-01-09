package com.example.demo.jvm01;

import java.io.*;
import java.lang.reflect.Method;

/**
 * @author Leo liang
 * @Date 2022/1/9
 */
public class MyClassloader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        Class<?> c = new MyClassloader().findClass("Hello");
        Method m = c.getMethod("hello");
        m.invoke(c.newInstance());
    }

    @Override
    protected Class<?> findClass(String name) {

        File file = new File("F:/Classloader/Hello.xlass");
        FileInputStream stream = null;
        byte[] bytes = null;
        try {
            stream = new FileInputStream(file);
            bytes = streamToByte(stream);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return defineClass(name, bytes, 0, bytes.length);
    }

    public static byte[] streamToByte(InputStream is) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int i = -1;
        try {
            while ((i = is.read(bytes)) != -1) {
                bos.write(bytes, 0, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }


}
