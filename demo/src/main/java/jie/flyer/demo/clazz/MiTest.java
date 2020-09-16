package jie.flyer.demo.clazz;

import java.lang.reflect.Constructor;

/**
 * @author kain
 * @since 2020-03-06
 */
public class MiTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Constructor<?>[] constructors = ClassTest.class.getConstructors();

        System.out.println(ClassTest.class);
    }
}
