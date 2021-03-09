package 冒泡排序;

import Sort公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: BubbleSort.java
 * 类的详细说明
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.3.9 - 上午 8:00
 * @label Sort
 */
public class BubbleSort {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组arr：[6,5,4,3,2,1]");
        while ((line = in.readLine()) != null) {
            int[] arr = PublicMethod.stringToIntegerArray(line);
            Solution.bubbleSort(arr);
            System.out.println(Arrays.toString(arr));
        }
    }
}

class Solution {
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {// 如果左边的数大于右边的数，则交换，保证右边的数字最大
                    arr[j + 1] = arr[j + 1] + arr[j];
                    arr[j] = arr[j + 1] - arr[j];
                    arr[j + 1] = arr[j + 1] - arr[j];
                }
            }
        }
    }
}

class SolutionCopy {
    public static void bubbleSort(int[] arr) {
        // 初始时 swapped 为 true，否则排序过程无法启动
        boolean swapped = true;
        // 如果没有发生过交换，说明剩余部分已经有序，排序完成
        for (int i = 0; i < arr.length - 1 && swapped; i++) {
            // 设置 swapped 为 false，如果发生交换，则将其置为 true
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 如果左边的数大于右边的数，则交换，保证右边的数字最大
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // 表示发生了交换
                    swapped = true;
                }
            }
        }
    }
}

class SolutionCopy2 {
    public static void bubbleSort(int[] arr) {
        boolean swapped = true;
        // 最后一个没有经过排序的元素的下标
        int indexOfLastUnsortedElement = arr.length - 1;
        // 上次发生交换的位置
        int swappedIndex = -1;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < indexOfLastUnsortedElement; i++) {
                if (arr[i] > arr[i + 1]) {
                    // 如果左边的数大于右边的数，则交换，保证右边的数字最大
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    // 表示发生了交换
                    swapped = true;
                    // 更新交换的位置
                    swappedIndex = i;
                }
            }
            // 最后一个没有经过排序的元素的下标就是最后一次发生交换的位置
            indexOfLastUnsortedElement = swappedIndex;
        }
    }
}