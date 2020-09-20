package 搜索旋转排序数组;
/**
 * FileName: SearchInRotatedSortedArray.java
 * 类的详细说明
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 可以假设数组中不存在重复的元素。
 * 时间复杂度必须是 O(log n) 级别。
 *
 * @label Array BinarySearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.27
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class SearchInRotatedSortedArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            int k = Integer.parseInt(in.readLine());
            int res = new SolutionCopy2().search(nums, k);
            String out = Integer.toString(res);
            System.out.println(out);
        }
    }

    private static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new int[0];
        String[] strings = input.split(",");
        int[] output = new int[strings.length];
        int index = 0;
        for (String string : strings) {
            output[index++] = Integer.valueOf(string);
        }
        //随机一点旋转
        output = rotateArray(output);
        System.out.println("旋转过后矩阵为\n"+Arrays.toString(output));
        return output;
    }

    private static int[] rotateArray(int[] input) {
        Random random = new Random();
        int step = random.nextInt(input.length);
        int[] temp = new int[input.length];
        for (int i = 0; i < temp.length; i++) {
            if (step == temp.length) step = 0;
            temp[i] = input[step++];
        }
        return temp;
    }
}

class Solution {
    //折半查找(BinarySearch)
    //关键字：排序、搜索
    //模式识别：有序或部分有序，基本使用二分搜索及其变种
    //算法描述：“丢弃”其一半的数据
    //复杂度 O(logN)O(1)
    public int search(int[] nums, int target) {
        if (nums == null) return -1;
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            //从有序边判断target是否在有序边范围内
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target <= nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if (target >= nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}

class SolutionCopy1 {
    //暴力解法，线性搜索数组
    //复杂度 O(N)O(1)
    public int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                return i;
        }
        return -1;
    }
}

class SolutionCopy2 {
    //DFS
    public int search(int[] nums, int target) {
        if (nums == null) return -1;
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;
        if (nums[left] <= nums[mid]) {
            if (nums[left] <= target && target <= nums[mid])
                return binarySearch(nums, left, mid - 1, target);
            else
                return binarySearch(nums, mid + 1, right, target);
        } else {
            if (nums[mid] <= target && target <= nums[right])
                return binarySearch(nums, mid + 1, right, target);
            else
                return binarySearch(nums, left, mid - 1, target);
        }
    }
}