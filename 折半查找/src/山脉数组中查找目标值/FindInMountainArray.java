package 山脉数组中查找目标值;
/**
 * FileName: FindInMountainArray.java
 * 类的详细说明
 * 给一个 山脉数组 mountainArr，返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
 * 如果不存在这样的下标 index，就返回 -1。(只能通过get方法获取值，时间复杂度必须是 O(log n) 级别）
 *
 * @label BinarySearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.29
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FindInMountainArray {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            MountainArray mountainArray = new MountainArray(nums);
            System.out.println("输入要查找的数字");
            int target = Integer.parseInt(in.readLine());
            int res = new Solution().findInMountainArray(target, mountainArray);
            String out = Integer.toString(res);
            System.out.println("要查找的数字位置为：" + out);
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
            output[index++] = Integer.parseInt(string);
        }
        return output;
    }
}

class Solution {
    //先使用二分法找到数组的峰值。
    //在峰值左边使用二分法寻找目标值。
    //如果峰值左边没有目标值，那么使用二分法在峰值右边寻找目标值。
    public int findInMountainArray(int target, MountainArray mountainArray) {
        MountainArray mArr = mountainArray;
        int len = mArr.length();
        if (len == 0)
            return -1;
        int peakIndex = findMountainTop(mArr, 0, len - 1);
        if (mArr.get(peakIndex) == target)
            return peakIndex;
        int res = findSortedArray(mArr, 0, peakIndex - 1, target);
        if (res != -1)
            return res;
        return findSortedArray(mArr, peakIndex + 1, len - 1, target);
    }

    /**
     * 在[left,right]查找target
     *
     * @param mArr
     * @param left
     * @param right
     * @param target
     * @return
     */
    private int findSortedArray(MountainArray mArr, int left, int right, int target) {
        if(right<mArr.length()-1){
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (mArr.get(mid) == target)
                    return mid;
                if (target < mArr.get(mid))
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }else {
            while(left<= right){
                int mid = left + (right - left) / 2;
                if (mArr.get(mid) == target)
                    return mid;
                if (target > mArr.get(mid))
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }
        return -1;
    }
        /**
         * 在[left...right]查找山顶元素的下标
         *
         * @param mArr
         * @param left
         * @param right
         * @return
         */
        private int findMountainTop(MountainArray mArr,int left, int right){
            while (left < right) {
                int mid = left + (right - left) / 2;//防止整形溢出
                if (mArr.get(mid) < mArr.get(mid + 1))
                    left = mid + 1;//下一轮搜索[mid+1...right]
                else
                    right = mid;//下一轮搜索[0...mid]
            }
            //left == right
            return left;
        }
    }

    //高山数组类
    class MountainArray {
        List<Integer> list = new ArrayList<>();

        MountainArray(int[] nums) {
            for (int num : nums) {
                this.list.add(num);
            }
        }

        public int get(int index) {
            return list.get(index);
        }

        public int length() {
            return list.size();
        }
    }