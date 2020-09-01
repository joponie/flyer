package demo.collection.set;

import lombok.*;
import org.junit.Test;

import java.util.*;

/**
 * @Author kain
 * @Date 2020/8/31
 **/
public class HashSetTest {

    @Test
    public void testHashMap() {
        Set<Person> personSet = new HashSet<>();
        personSet.add(new Person(1, "张三"));
        personSet.add(new Person(2, "李四"));
        personSet.add(new Person(2, "李四"));
        for (Person person : personSet) {
            System.out.println(person);
        }
    }

    @Test
    public void testTreeMap() {
        Set<Person> personSet = new TreeSet<>();
        personSet.add(new Person(1, "张三"));
        personSet.add(new Person(2, "李四"));
        personSet.add(new Person(2, "李四"));
        for (Person person : personSet) {
            System.out.println(person);
        }
    }

    @ToString
    @AllArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    public static class Person implements Comparable<Person>{

        private Integer id;

        private String name;

        @Override
        public int compareTo(Person o) {
            return o.name.compareTo(this.name);
        }
    }

}
