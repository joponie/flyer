package jie.flyer.demo.ext;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * @Author kain
 * @Date 2020/8/5
 **/
public class Child extends Base {

    public int m;

    private void test(){
    }

    public static void main(String[] args) throws IllegalAccessException {
        Child child = new Child();
        child.setM(30);
        Field[] fields = Child.class.getDeclaredFields();
        Field field1 = fields[0];
        field1.setAccessible(true);
        field1.set(child, 21);
        System.out.println(child.m);
        Base base = child;
        System.out.println(base.m);
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

}
