package jie.flyer.demo.sort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Author kain
 * @Date 2020/11/24
 **/
public class Sort {

    public static final int MAX_ARRAY_SIZE = 15000;

    public static void main(String[] args) throws Exception {
        File inputFile = new File("/Users//qudian//Workspace//inputFile.txt");
        File outputFile = new File("/Users//qudian//Workspace//outFile.txt");
        File tempDir = new File("/Users//qudian//Workspace//tempFile");
        generateData(inputFile);
        sort(inputFile, outputFile, tempDir);
    }

    private static void sort(File inputFile, File outputFile, File tempDir) throws IOException {
        List<File> tempFiles = segmentSort(inputFile, tempDir);
        merge(tempFiles, outputFile);
    }

    private static void merge(List<File> tempFiles, File outputFile) {

    }

    private static List<File> segmentSort(File inputFile, File tempDir) throws IOException {
        List<File> tempFiles = new ArrayList<>();

        BufferedReader bufr = new BufferedReader(new FileReader(inputFile));
        String[] heapArray = new String[MAX_ARRAY_SIZE];
        String line = null;
        int i = 0;
        while ((line = bufr.readLine()) != null) {
            heapArray[i++] = line;
            if (i == MAX_ARRAY_SIZE) {
                Arrays.sort(heapArray);
                File tempFile = File.createTempFile("tempFile", ".txt", tempDir);
                BufferedWriter bufw = new BufferedWriter(new FileWriter(tempFile));
                while (i > 0) {
                    bufw.write(heapArray[--i]);
                    bufw.newLine();
                }
                heapArray = new String[MAX_ARRAY_SIZE];
            }
        }
        if (i >= 0) {
            Arrays.sort(heapArray, 0, i);
            File tempFile = File.createTempFile("tempFile", ".txt", tempDir);
            BufferedWriter bufw = new BufferedWriter(new FileWriter(tempFile));
            while (i > 0) {
                bufw.write(heapArray[--i]);
                bufw.newLine();
            }
            bufw.flush();
            heapArray = null;
        }
        return tempFiles;
    }


    public static void generateData(File file) throws IOException {
        final int MAX = 20000;
        if (file.exists()) {
            file.delete();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < MAX; ++i) {
            writer.write(getRandomString());
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    public static String getRandomString() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            sb.append((char) (random.nextInt(26) + 97));

        }
        return sb.toString();
    }
}
