package 快速排序;

import Sort公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: QuickSort.java
 * 类的详细说明
 * 使用快速排序对数组排序
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.22 - 下午 9:05
 * @label Sort Merge Recursive
 */
public class QuickSort {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个待排序的整型数组nums：[4,6,3,7,2,1,5]");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            new Solution().quickSort(nums);
            System.out.println(Arrays.toString(nums));
        }
    }
}

/**
 * 选取一个关键字，通过一趟排序将整段序列分割成独立两部分，其中一部分均比另一部分小
 * 然后分别对这两部分进行排序，从而达到整个序列有序
 */
class Solution {

    public void quickSort(int[] nums) {
        int len;
        if (nums == null || (len = nums.length) < 2) {
            return;
        }
        quickSort(nums, 0, len - 1);
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        // base中存放基准数
        int key = nums[left];
        int i = left, j = right;
        while (i < j) {
            // 从右往左找比key小的数
            while (nums[j] >= key && i < j) {
                j--;
            }
            // 从左往右找比key大的数
            while (nums[i] <= key && i < j) {
                i++;
            }
            // 交换两个数在数组中的位置
            if (i < j) {
                nums[i] = nums[i] + nums[j] - (nums[j] = nums[i]);
            }
        }
        // 将基准数放到中间位置（基数归位）
        nums[left] = nums[i];
        nums[i] = key;
        // 对基准数两旁执行和上面同样的操作
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

}
