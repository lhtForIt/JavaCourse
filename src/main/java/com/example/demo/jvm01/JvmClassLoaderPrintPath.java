package com.example.demo.jvm01;

import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * @author Leo liang
 * @Date 2022/1/9
 */
public class JvmClassLoaderPrintPath {


    public static void main(String[] args) {

        //启动类加载器
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for (URL url : urls) {
            System.out.println(" ===> " + url.toExternalForm());
        }

        //扩展类加载器
        printClassloader("扩展类加载器", JvmClassLoaderPrintPath.class.getClassLoader().getParent());
        printClassloader("应用类加载器", JvmClassLoaderPrintPath.class.getClassLoader());
    }

    private static void printClassloader(String name, ClassLoader classloader) {
        System.out.println();
        if (classloader != null) {
            System.out.println(name + " Classloader -> " + classloader.toString());
            printURLForClassloader(classloader);
        } else {
            System.out.println(name+" Classloader -> null");
        }
    }

    private static void printURLForClassloader(ClassLoader classloader) {
        Object ucp = insightField(classloader, "ucp");
        Object path = insightField(ucp, "path");
        List paths = (List) path;
        for (Object p : paths) {
            System.out.println(" ===> " + p.toString());
        }
    }


    private static Object insightField(Object obj, String fName) {
        Field f = null;
        try {
            if (obj instanceof URLClassLoader) {
                f = URLClassLoader.class.getDeclaredField(fName);
            } else {
                f = obj.getClass().getDeclaredField(fName);
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {

        }

        return null;
    }


}
