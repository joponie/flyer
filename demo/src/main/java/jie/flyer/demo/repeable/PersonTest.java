package jie.flyer.demo.repeable;

import java.lang.annotation.Annotation;

/**
 * @Author 刘杰鹏
 * @Date 2020/8/25
 **/
@Person("红发")
@Person("黑胡子")
public class PersonTest {

    public static void main(String[] args) {
        Persons annotation = PersonTest.class.getAnnotation(Persons.class);
        for (Person person : annotation.value()) {
            System.out.println(person.value());
        }

        Annotation[] annotations = PersonTest.class.getAnnotations();
        for (Annotation annotation1 : annotations) {
            System.out.println(annotation.annotationType());
        }

    }
}
