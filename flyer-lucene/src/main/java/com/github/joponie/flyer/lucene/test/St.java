package com.github.joponie.flyer.lucene.test;

import java.util.Arrays;

/**
 * @author 刘杰鹏
 * @since 2020/3/28
 */
public class St {
    public static void main(String[] args) {
//        int[] arr = {1, 4, 5, 67, 2, 7, 8, 6, 9, 44};
        int[] arr = {1, 4, 2};
        quickSort2(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     *
     * @param arrays
     */
    private static void sort1(int[] arrays) {
        int change;
        int temp;
        for (int i = 0; i < arrays.length; i++) {
            change = 0;
            for (int i1 = 0; i1 < arrays.length - 1 - i; i1++) {
                if (arrays[i1] > arrays[i1 + 1]) {
                    temp = arrays[i1];
                    arrays[i1] = arrays[i1 + 1];
                    arrays[i1 + 1] = temp;
                    change = 1;
                }
            }
            if (change == 0) {
                break;
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arrays
     */
    private static void sort2(int[] arrays) {
        int index;
        int temp;
        for (int i = 0; i < arrays.length; i++) {
            index = 0;
            for (int i1 = 0; i1 < arrays.length - i; i1++) {
                if (arrays[i1] > arrays[index]) {
                    index = i1;
                }
            }
            temp = arrays[index];
            arrays[index] = arrays[arrays.length - 1 - i];
            arrays[arrays.length - 1 - i] = temp;
        }
    }

    /**
     * 插入排序
     *
     * @param arrays
     */
    private static void insertSort(int[] arrays) {
        if (arrays.length == 1) {
            return;
        }
        int temp;
        for (int i = 1; i < arrays.length; i++) {
            temp = arrays[i];
            int j;
            for (j = i; j > 0 && temp <= arrays[j - 1]; j--) {
                arrays[j] = arrays[j - 1];
            }
            arrays[j] = temp;
        }
    }

    private static void quickSort(int[] arr, int L, int R) {
        int i = L;
        int j = R;
        int p = arr[(i + j) / 2];
        while (i <= j) {
            while (p > arr[i]) {
                i++;
            }
            while (p < arr[j]) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if (i > L) {
            quickSort(arr, L, j);
        }
        if (j < R) {
            quickSort(arr, i, R);
        }
    }

    private static void mergeSort(int[] arrays, int L, int R) {
        if (L == R) {
            return;
        }
        int M = (L + R) / 2;
        mergeSort(arrays, L, M);
        mergeSort(arrays, M + 1, R);
        merge(arrays, L, M + 1, R);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int index = l;
        int[] larr = Arrays.copyOfRange(arr, l, m);
        int[] rarr = Arrays.copyOfRange(arr, m, r + 1);
        int i = 0;
        int j = 0;
        while (i < larr.length && j < rarr.length) {
            if (larr[i] < rarr[j]) {
                arr[index] = larr[i];
                i++;
            } else {
                arr[index] = rarr[j];
                j++;
            }
            index++;
        }
        while (i < larr.length) {
            arr[index] = larr[i];
            i++;
            index++;
        }
        while (j < rarr.length) {
            arr[index] = rarr[j];
            j++;
            index++;
        }
    }

    private static void quickSort2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //支点
        int qua = arr[(L + R) / 2];
        int l = L, r = R;
        while (l <= r) {
            while (qua > arr[l]) {
                l++;
            }
            while (qua < arr[r]) {
                r--;
            }
            if (l <= r) {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
                l++;
                r--;
            }
        }
        quickSort2(arr, L, r);
        quickSort2(arr, l, R);
    }
}
