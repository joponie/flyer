package jie.flyer.demo.nio;

import sun.nio.ch.IOUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author kain
 * @Date 2020/9/17
 **/
public class FileChannelTest {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("/Users/qudian/Workspace/code/owner/flyer/demo/src/main/java/jie/flyer/demo/jvm/classloader/CustomClassloader.java");
        FileChannel channel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (channel.read(buffer) > 0) {
            System.out.print(new String(buffer.array()));
            buffer.clear();
        }
        channel.close();

    }

}
