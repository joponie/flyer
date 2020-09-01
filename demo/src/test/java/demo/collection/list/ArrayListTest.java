package demo.collection.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 刘杰鹏
 * @Date 2020/8/31
 **/
public class ArrayListTest {

    @Test
    public void testArrayList() {
        List l = new ArrayList();
        l.add(1);
        l.add("Str");
        for (Object o : l) {
            System.out.println(o);
        }

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(1);

        list.remove(1);

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

        list.stream().forEach(i-> System.out.println(i));

        list.forEach(System.out::println);
    }

}
