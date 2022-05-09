package com.course.week01;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * 自定义ClassLoader, 加载Hello.xclass
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-05-08 09:08:07
 */
public class MyHelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Object obj = new MyHelloClassLoader().findClass("Hello").newInstance();
        System.out.println("obj : " + obj);
        final Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method m : methods) {
            if ("Hello".equalsIgnoreCase(m.getName())) {
                m.invoke(obj);
            }
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        final String helloBase64 = "";
//        final byte[] bytes = decode(helloBase64);
        final byte[] bytes = loadByte();
        diffHandle(bytes);
        return super.defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 如果输入base64而不是class文件, 可以更安全地自定义加载
     *
     * @param base64
     * @return
     */
    private byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    private byte[] loadByte() {
        try {
            final FileInputStream fis = new FileInputStream("Hello.xlass");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * xlass文件中存储的字节是255-x的结果，所以我们继续使用255-x来读取，从而获取原class字节码文件
     *
     * @param data
     */
    private void diffHandle(byte[] data) {
        final int length = data.length;
        for (int i = 0; i < length; i++) {
            data[i] = (byte) (SUM - (data[i] & 0xFF));
        }
    }

    private static final int SUM = 255;
}
