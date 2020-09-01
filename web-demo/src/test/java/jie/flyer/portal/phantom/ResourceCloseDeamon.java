package jie.flyer.portal.phantom;

import java.io.Closeable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kain
 * @since 2020-02-09
 */
public class ResourceCloseDeamon extends Thread {

    private static ReferenceQueue QUEUE = new ReferenceQueue();

    //保持对reference的引用,防止reference本身被回收
    private static List<Reference> references = new ArrayList<>();

    public static void register(Object referent, List<Closeable> closeables) {
        references.add(new ResourcePhantomReference(referent, QUEUE, closeables));
    }

    @Override
    public void run() {
        this.setName("ResourceCloseDeamon");
        while (true) {
            try {
                System.out.println("开始运行.....");
                //remove是阻塞方法，会一直等到可以获取到元素
                ResourcePhantomReference reference = (ResourcePhantomReference) QUEUE.remove();
                reference.cleanUp();
                references.remove(reference);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}