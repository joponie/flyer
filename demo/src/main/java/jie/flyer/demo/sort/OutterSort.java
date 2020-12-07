package jie.flyer.demo.sort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author kain
 * @Date 2020/11/24
 **/
public class OutterSort {

    static final int maxSize = 1500;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        File inputFile = new File("/Users//qudian//Workspace//inputFile.txt");
        File outputFile = new File("/Users//qudian//Workspace//outFile.txt");
        File tempFile = new File("/Users//qudian//Workspace//tempFile");
        generateData(inputFile);
        sort(inputFile, outputFile, tempFile);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void sort(File inputFile, File outputFile, File tempFile) throws Exception {
        BufferedReader bufr = new BufferedReader(new FileReader(inputFile));
        String[] heapArray = new String[maxSize];
        String line;
        int i = 0;
        List<File> tempFiles = new ArrayList<>();

        int heapSize;
        while ((line = bufr.readLine()) != null) {
            heapArray[i++] = line;
            if (i == maxSize) {
                break;
            }
        }
        while (i == maxSize) {
            heapSize = maxSize;
            File newTempFile = File.createTempFile("tempFile", ".txt", tempFile);
            tempFiles.add(newTempFile);
            BufferedWriter bufw = new BufferedWriter(new FileWriter(newTempFile));
            buildHeap(heapArray, heapSize, 0);
            while (heapSize != 0) {
                bufw.write(heapArray[0]);
                bufw.newLine();
                line = bufr.readLine();
                if (line == null)
                    break;
                if (line.compareTo(heapArray[0]) > 0) {
                    heapArray[0] = line;
                } else {
                    heapArray[0] = heapArray[heapSize - 1];
                    heapArray[heapSize - 1] = line;
                    heapSize--;
                }
                siftDown(heapArray, 0, heapSize);
            }
            if (heapSize != 0) {
                i = i - heapSize;
                while (heapSize != 0) {
                    bufw.write(heapArray[0]);
                    bufw.newLine();
                    heapArray[0] = heapArray[heapSize - 1];
                    heapSize--;
                    siftDown(heapArray, 0, heapSize);
                }
            }
            bufw.close();
        }
        if (i != 0) {
            heapSize = i;
            File newTempFile = File.createTempFile("tempFile", ".txt", tempFile.getParentFile());
            tempFiles.add(newTempFile);
            BufferedWriter bufw = new BufferedWriter(new FileWriter(newTempFile));
            int offset = maxSize - heapSize;
            buildHeap(heapArray, heapSize, offset);
            while (heapSize != 0) {
                bufw.write(heapArray[offset]);
                bufw.newLine();
                heapArray[offset] = heapArray[offset + heapSize - 1];
                heapSize--;
                siftDown(heapArray, offset, heapSize);

            }
            bufw.close();
        }

        System.gc();
        multiWayMergeSort(tempFiles, outputFile);

        for (File file : tempFiles) {
            file.delete();
        }
    }

    private static void buildHeap(String heapArray[], int size, int start) {
        for (int i = size / 2 - 1; i >= start; i--) {
            siftDown(heapArray, i, size);
        }
    }

    private static void siftDown(String[] heapArray, int i, int size) {
        int j = 2 * i + 1;
        String temp = heapArray[i];
        while (j < size) {
            if (j < size - 1 && (heapArray[j].compareTo(heapArray[j + 1])) > 0)
                ++j;
            if (temp.compareTo(heapArray[j]) > 0) {
                heapArray[i] = heapArray[j];
                i = j;
                j = 2 * j + 1;
            } else break;
        }
        heapArray[i] = temp;
    }

    static void multiWayMergeSort(List<File> files, File outputFile) throws IOException {
        int ways = files.size();
        int length_per_run = maxSize / ways;
        Run[] runs = new Run[ways];
        for (int i = 0; i < ways; i++) {
            runs[i] = new Run(length_per_run);
        }
        List<BufferedReader> rList = new ArrayList<>();
        for (int i = 0; i < ways; i++) {
            BufferedReader bufr = new BufferedReader(new FileReader(files.get(i)));
            rList.add(i, bufr);
            int j = 0;
            while ((runs[i].buffer[j] = bufr.readLine()) != null) {
                ++j;
                if (j == length_per_run)
                    break;
            }
            runs[i].length = j;
            runs[i].index = 0;
        }
        int[] ls = new int[ways];
        createLoserTree(ls, runs, ways);
        BufferedWriter bufw = new BufferedWriter(new FileWriter(outputFile));
        int liveRuns = ways;
        while (liveRuns > 0) {
            bufw.write(runs[ls[0]].buffer[runs[ls[0]].index++]);
            bufw.newLine();
            if (runs[ls[0]].index == runs[ls[0]].length) {
                int j = 0;
                while ((runs[ls[0]].buffer[j] = rList.get(ls[0]).readLine()) != null) {
                    j++;
                    if (j == length_per_run) {
                        break;
                    }
                }
                runs[ls[0]].length = j;
                runs[ls[0]].index = 0;
            }
            if (runs[ls[0]].length == 0) {
                liveRuns--;
                String maxString = "";
                maxString += "\n";
                runs[ls[0]].buffer[runs[ls[0]].index] = maxString;
            }
            adjust(ls, runs, ways, ls[0]);
        }
        bufw.flush();
        bufw.close();
        for (BufferedReader bufr : rList
        ) {
            bufr.close();
        }

    }

    private static void createLoserTree(int[] ls, Run[] runs, int n) {
        for (int i = 0; i < n; i++) {
            ls[i] = -1;
        }
        for (int i = n - 1; i >= 0; i--) {
            adjust(ls, runs, n, i);
        }
    }

    private static void adjust(int[] ls, Run[] runs, int n, int s) {
        int t = (s + n) / 2;
        int temp = 0;
        while (t != 0) {
            if (s == -1)
                break;
            if (ls[t] == -1 || (runs[s].buffer[runs[s].index].compareTo(runs[ls[t]].buffer[runs[ls[t]].index])) > 0) {
                temp = s;
                s = ls[t];
                ls[t] = temp;
            }
            t /= 2;
        }
        ls[0] = s;
    }

    private static void generateData(File inputFile) throws IOException {
        final int MAX = 800000;
        BufferedWriter bufw = new BufferedWriter(new FileWriter(inputFile));
        for (int i = 0; i < MAX; ++i) {
            bufw.write(getRandomString());
            bufw.newLine();
        }
        bufw.flush();
        bufw.close();
    }

    private static String getRandomString() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 255; i++) {
            sb.append((char) (random.nextInt(26) + 97));
        }
        return sb.toString();
    }

    static class Run {
        String[] buffer;
        int length;
        int index;

        Run(int length) {
            this.length = length;
            buffer = new String[length];
        }
    }
}
