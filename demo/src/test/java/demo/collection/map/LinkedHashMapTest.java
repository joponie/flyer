package demo.collection.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author kain
 * @Date 2020/8/31
 **/
public class LinkedHashMapTest {

    @Test
    public void testHashMap() {
        Map<Person, Integer> personMap = new LinkedHashMap<>();
        personMap.put(new Person(2, "李四"), 2);
        personMap.put(new Person(1, "张三"), 1);
        personMap.put(new Person(1, "张三"), 3);

        for (Map.Entry<Person, Integer> entry : personMap.entrySet()) {
            System.out.println("key:" + entry.getKey() + "-- value:" + entry.getValue());
        }
    }

    public static class LRUCache<K,V> extends LinkedHashMap<K, V> {

        private int maxEntries;

        public LRUCache(int maxEntries) {
            super(16, 0.75f, true);
            this.maxEntries = maxEntries;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > maxEntries;
        }
    }

    @ToString
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Person {

        private Integer id;

        private String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(id, person.id) &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

}
