package jie.flyer.demo.repeable;

import java.lang.annotation.*;

/**
 * @Author 刘杰鹏
 * @Date 2020/8/25
 **/
@Repeatable(value = Persons.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Person {

    String value() default "";

}
