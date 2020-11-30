package jie.flyer.demo.sort;

import java.io.*;
import java.util.*;

/**
 * @Author kain
 * @Date 2020/11/24
 **/
public class ExternalSort {

    public static final int LINE_NUM = 20000000;

    private static final int HEAP_SIZE = 100000;

    public static final String INPUT_FILE = "C:\\dev\\sort\\inputFile.txt";

    public static final String OUT_FILE = "C:\\dev\\sort\\outFile.txt";

    public static final String TEMP_DIR = "C:\\dev\\sort\\temp";

    public static void main(String[] args) throws Exception {
        File inputFile = new File(INPUT_FILE);
        File outputFile = new File(OUT_FILE);
        File tempDir = new File(TEMP_DIR);
        generateData(inputFile);
        long start = System.currentTimeMillis();
        sort(inputFile, outputFile, tempDir);
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void sort(File inputFile, File outputFile, File tempDir) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String[] heapArray = new String[HEAP_SIZE];
        String line;
        int i = 0;
        List<File> tempFiles = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            heapArray[i++] = line;
            if (i == HEAP_SIZE) {
                tempFiles.add(writeTempFile(tempDir, heapArray, i));
                i = 0;
                heapArray = new String[HEAP_SIZE];
            }
        }
        if (i != 0) {
            tempFiles.add(writeTempFile(tempDir, heapArray, i));
        }
        heapArray = null;
        System.gc();
        mergeSort(tempFiles, outputFile);

        for (File tempFile : tempFiles) {
            tempFile.delete();
        }
    }

    private static void mergeSort(List<File> files, File outputFile) throws IOException {
        int ways = files.size();
        int perRunLength = HEAP_SIZE / ways;

        PriorityQueue<TempRun> queue = new PriorityQueue<>(ways);
        for (File file : files) {
            queue.add(new TempRun(perRunLength, file));
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
        while (!queue.isEmpty()) {
            TempRun tempRun = queue.poll();
            bw.write(tempRun.getCurrent());
            bw.newLine();
            if (tempRun.isLive()) {
                queue.add(tempRun);
            }
        }
        bw.flush();
        bw.close();
    }

    private static File getTempFile(File tempDir) throws IOException {
        return File.createTempFile("tempDir", ".txt", tempDir);
    }

    private static File writeTempFile(File tempDir, String[] heapArray, int i) throws IOException {
        Arrays.sort(heapArray, 0, i, Comparator.reverseOrder());
        File tempFile = getTempFile(tempDir);

        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        while (i > 0) {
            bw.write(heapArray[--i]);
            if (i > 0) {
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        return tempFile;
    }

    private static void generateData(File inputFile) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile));
        for (int i = 1; i <= LINE_NUM; ++i) {
            bw.write(getRandomString());
            if (i != LINE_NUM) {
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }

    private static String getRandomString() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = random.nextInt(255);
        for (int i = 0; i <= length; i++) {
            sb.append((char) (random.nextInt(26) + 97));
        }
        return sb.toString();
    }


    public static class TempRun implements Comparable<TempRun> {

        private BufferedReader br;

        private String[] buffer;

        private final int limit;

        private int length;

        private int index;

        public TempRun(int limit, File file) throws IOException {
            this.limit = limit;
            this.br = new BufferedReader(new FileReader(file));
            this.buffer = new String[limit];

            int i = 0;
            String line;
            while ((line = br.readLine()) != null) {
                buffer[i++] = line;
                if (i == limit) {
                    break;
                }
            }
            this.index = 0;
            this.length = i;
        }

        public String getCurrent() {
            return buffer[index];
        }

        public boolean isLive() throws IOException {
            if (index < length - 1) {
                index++;
                return true;
            }
            int i = 0;
            String line;
            while ((line = br.readLine()) != null) {
                buffer[i++] = line;
                if (i == limit) {
                    break;
                }
            }
            if (i == 0) {
                return false;
            }
            this.index = 0;
            this.length = i;
            return true;
        }

        @Override
        public int compareTo(TempRun o) {
            return getCurrent().compareTo(o.getCurrent());
        }
    }
}
