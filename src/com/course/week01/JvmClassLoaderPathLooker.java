package com.course.week01;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-05-08 08:34:30
 */
public class JvmClassLoaderPathLooker {

    public static void main(String[] args) {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        System.out.println("BootstrapClassLoader...");
        for (URL url : urls) {
            System.out.println(" external form ===> " + url.toExternalForm());
        }
        for (URL url : urls) {
            System.out.println(" path ===> " + url.getPath());
        }

        printClassLoader("App", JvmClassLoaderPathLooker.class.getClassLoader());
        printClassLoader("Ext", JvmClassLoaderPathLooker.class.getClassLoader().getParent());

    }

    private static void printClassLoader(final String name, final ClassLoader classLoader) {
        System.out.println();
        System.out.println(name + " ClassLoader --> " + classLoader);
        if (classLoader != null) {
            printURLForClassLoader(classLoader);
        }
    }

    private static void printURLForClassLoader(final ClassLoader classLoader) {
        Object ucp = insightField(classLoader, "ucp");
        Object path = insightField(ucp, "path");
        for (Object p : (List) path) {
            System.out.println(" ===> " + p);
        }
    }

    private static Object insightField(final Object obj, final String fName) {
        try {
            final Class clazz = (obj instanceof URLClassLoader) ? URLClassLoader.class : obj.getClass();
            final Field f = clazz.getDeclaredField(fName);
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
