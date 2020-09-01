package demo.collection.list;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author 刘杰鹏
 * @Date 2020/8/31
 **/
public class LinkedListTest {


    @Test
    public void testLinkedList() {
        List<String> list = new LinkedList<>();

        list.add("a");
        list.add("b");
        list.add("c");

        System.out.println(list.get(1));
    }

    /**
     * 1.获取并移除
     *
     * poll() 　　获取并移除此队列的头，如果此队列为空，则返回 null
     * remove()　　获取并移除此队列的头，如果此队列为空，则抛出NoSuchElementException异常
     * 2.获取但不移除
     *
     * peek()　　获取队列的头但不移除此队列的头。如果此队列为空，则返回 null
     * element()　　获取队列的头但不移除此队列的头。如果此队列为空，则将抛出NoSuchElementException异常
     * 添加元素的方法
     *
     * offer()　　将指定的元素插入此队列（如果立即可行且不会违反容量限制），插入成功返回 true；否则返回 false。当使用有容量限制的队列时，offer方法通常要优于 add方法——add方法可能无法插入元素，而只是抛出一个  IllegalStateException异常
     * add()　　将指定的元素插入此队列
     */
    @Test
    public void testQueue() {
        Queue<String> queue = new LinkedList<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");

        while (queue.peek() != null) {
            System.out.println(queue.poll());
        }
    }

}
