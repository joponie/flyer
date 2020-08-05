package jie.flyer.portal.phantom;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author kain
 * @since 2020-02-09
 */
public class PhantomTest {
    public static void main(String[] args) throws Exception {
        //打开回收
        ResourceCloseDeamon deamon = new ResourceCloseDeamon();
        deamon.setDaemon(true);
        deamon.start();

        // touch a.txt b.txt
        // echo “hello” > a.txt

        //保留对象,防止gc把stream回收掉,其不到演示效果
        List<Closeable> all = new ArrayList<>();
        FileInputStream inputStream;
        FileOutputStream outputStream;

        for (int i = 0; i < 100000; i++) {
            inputStream = new FileInputStream("/Users/robin/a.txt");
            outputStream = new FileOutputStream("/Users/robin/b.txt");
            FileOperation operation = new FileOperation(inputStream, outputStream);
            operation.operate();
            TimeUnit.MILLISECONDS.sleep(100);

            List<Closeable> closeables = new ArrayList<>();
            closeables.add(inputStream);
            closeables.add(outputStream);
            all.addAll(closeables);
            /**
             * 虚引用中被引用的对象（此例指的是operation对象）什么时候被回收？
             * 在下次循环的时候，会被回收
             * operation对象在本次循环中还有强引用，不会被回收
             * 下次循环的时候，因为operation是局部变量，会消失，就没有了强引用。
             * 这个时候就只有虚引用，发生gc就会被gc回收掉。
             */
            ResourceCloseDeamon.register(operation, closeables);
            //用下面命令查看文件句柄,如果把上面register注释掉,就会发现句柄数量不断上升
            /**
             * 所以创建在堆中的对象（operation）会不断的被回收，堆中的使用率不会大幅度上升。
             */
            //jps | grep PhantomTest | awk ‘{print $1}’ |head -1 | xargs  lsof -p  | grep /User/robin
            System.gc();
        }
    }
}
