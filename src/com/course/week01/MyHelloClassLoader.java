package com.course.week01;

import java.io.FileInputStream;
import java.util.Base64;

/**
 * 自定义ClassLoader, 加载Hello.xclass
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-05-08 09:08:07
 */
public class MyHelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        new MyHelloClassLoader().findClass("Hello").newInstance();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        final String helloBase64 = "";
//        final byte[] bytes = decode(helloBase64);
        final byte[] bytes = loadByte();
//        final byte[] bytes = decode(new String(loadByte()));
        return super.defineClass(name, bytes, 0, bytes.length);
    }

    /**
     * 如果输入base64而不是class文件, 可以更安全地自定义加载
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
}
