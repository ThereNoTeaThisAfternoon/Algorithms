package 合并两个有序数组;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: MergeSortedArray.java
 * 类的详细说明
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，
 * 这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[i] <= 109
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.6 - 上午 11:41
 * @label Array TwoPointer
 */
public class MergeSortedArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型有序数组nums1：[1,2,3,0,0,0]");
        while ((line = in.readLine()) != null) {
            int[] nums1 = new PublicMethod().stringToIntegerArray(line);
            System.out.println("请输入一个整形有序数组nums2：[2,4,5]");
            int[] nums2 = new PublicMethod().stringToIntegerArray(in.readLine());
            int m, n;
            m = nums1.length - (n = nums2.length);
            new SolutionCopy().merge(nums1, m, nums2, n);
            System.out.println("合并后：" + Arrays.toString(nums1));
        }
    }
}

/**
 * 使用额外空间，从左到右比较合并。
 */
class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // make a copy of nums1
        int[] temp = new int[m];
        System.arraycopy(nums1, 0, temp, 0, m);
        // two pointers for temp and nums2
        int i = 0, j = 0;
        // set pointers for nums1
        int index = 0;
        // Compare elements from temp and nums2, and add the smallest one into nums1
        while (i < m && j < n) {
            nums1[index++] = temp[i] < nums2[j] ? temp[i++] : nums2[j++];
        }
        // if there are still elements to add
        System.arraycopy(temp, i, nums1, index, m - i);
        System.arraycopy(nums2, j, nums1, index, n - j);
    }
}

/**
 * 从右向左依次合并
 */
class SolutionCopy {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        int index = m + n - 1;
        // compare elements from nums1 and nums2, and add the largest one in nums1
        while (i >= 0 && j >= 0) {
            nums1[index--] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
        }
        // 如果num2有剩余元素，说明是都小于num1的，因此直接放到最前方即可
        // add missing elements from nums2
        System.arraycopy(nums2, 0, nums1, 0, j + 1);
    }
}

/**
 * sorted after merge
 */
class SolutionCopy2 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
}

