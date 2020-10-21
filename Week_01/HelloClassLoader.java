package follow.phenix.ice.weekOne;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author hejinhua
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        String className = "/home/hejinhua/Projects/ice/src/main/java/follow/phenix/ice/weekOne/Hello.xlass";
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class<?> helloClass = helloClassLoader.loadClass(className);
        Object instance = helloClass.newInstance();
        Method helloMethod = helloClass.getMethod("hello");
        helloMethod.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] byteArr = new byte[100];
        try {
            byteArr = loadClassByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defineClass("Hello", byteArr, 0, byteArr.length);
    }

    private byte[] loadClassByName(String name) throws Exception {
        File file = new File(name);
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue;
        while ((nextValue = fileInputStream.read()) != -1) {
            byteStream.write(255 - nextValue);
        }
        return byteStream.toByteArray();
    }
}
