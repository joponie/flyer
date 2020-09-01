package jie.flyer.demo.repeable;

import java.lang.annotation.*;

/**
 * @Author 刘杰鹏
 * @Date 2020/8/25
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Persons {

    Person[] value();

}
