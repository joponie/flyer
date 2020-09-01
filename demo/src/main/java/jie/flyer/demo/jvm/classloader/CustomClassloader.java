package jie.flyer.demo.jvm.classloader;

/**
 * @author kain
 * @since 2020-04-09
 */
public class CustomClassloader extends ClassLoader{

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
