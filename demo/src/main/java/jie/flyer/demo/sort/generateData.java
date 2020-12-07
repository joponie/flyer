package jie.flyer.demo.sort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * @Author kain
 * @Date 2020/11/24
 **/
public class generateData {

    public static void main(String[] args) throws IOException {
        final int MAX = 800000;
        File f = new File("/Users//qudian//Workspace//inputFile.txt");
        if (f.exists())
            f.delete();
        BufferedWriter bufw = new BufferedWriter(new FileWriter(f));
        for (int i = 0; i < MAX; ++i) {
            bufw.write(getRandomString());
            bufw.newLine();
        }
        bufw.flush();
        bufw.close();
    }

    public static String getRandomString() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 255; i++) {
            sb.append((char) (random.nextInt(26) + 97));
        }
        return sb.toString();
    }
}
