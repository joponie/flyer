package jie.flyer.demo.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 刘杰鹏
 * @Date 2020/8/31
 **/
public class ArrayListTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(1);

        list.remove(4);

        list.remove(Integer.valueOf(1));
        System.out.println(list.get(0));

        System.out.println("foreach");
        for (Integer i : list) {
            System.out.println(i);
        }

        System.out.println("for");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("java8");
        list.forEach(System.out::println);
    }

}
