package 数组中的逆序对;
/**
 * FileName: ReversePairsOfArray.java
 * 类的详细说明
 * 在数组中的两个数字，如果前面的一个数字大于后面的一个数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。（不能用暴力破解）（归并排序）
 *
 * @label Array DivideAndConquer(分治法)
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.24
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReversePairsOfArray {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            int ret = new SolutionCopy1().reversePairs(nums);
            String out = String.valueOf(ret);
            System.out.println(out);
        }
    }

    private static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new int[0];
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        int i = 0;
        for (String part : parts) {
            part = part.trim();
            output[i++] = Integer.parseInt(part);
        }
        return output;
    }
}

class Solution {
    //暴力破解,没什么用
    public int reversePairs(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) res++;
            }
        }
        return res;
    }
}

class SolutionCopy1 {
    /**
     * 分治法(Divide and conquer)
     * mergeSorting(归并排序)
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        int len = nums.length;
        if (len < 2) return 0;
        int[] copy = new int[len];
        System.arraycopy(nums,0,copy,0,len);
        int[] temp = new int[len];//全局仅创建一个铺助数组，节约空间开销，解决创建和销毁资源消耗，还能解决子区间数组下标的偏移
        return reversePairs(copy, 0, len - 1, temp);
    }

    /**
     * nums[left...right] 计算逆序对个数并且排序
     *
     * @param nums
     * @param left
     * @param right
     * @param temp
     * @return
     */
    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right) return 0;
        int mid = left + (right - left) / 2;//如果数值较大直接相加可能溢出；

        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        if (nums[mid] <= nums[mid + 1])
            return leftPairs + rightPairs;
        return leftPairs + rightPairs + mergeAndCount(nums, left, mid, right, temp);
    }

    /**
     * nums[left...mid]有序，nums[mid+1...right]有序；
     *
     * @param nums
     * @param left
     * @param mid
     * @param right
     * @param temp
     * @return
     */
    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        for (int i = left; i <= right; i++) {//区间拷贝
            temp[i] = nums[i];
        }
        int i = left;
        int j = mid + 1;//两个子区间第一个元素下标
        int count = 0;//保存结果
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j++];
            } else if (j == right + 1) {
                nums[k] = temp[i++];
            } else if (temp[i] <= temp[j]) {//如果两个元素相等，前一个先归并回去，防止重复计数，提高归并排序的稳定性
                nums[k] = temp[i++];
            } else {
                nums[k] = temp[j++];
                count += mid - i + 1;
            }
        }
        return count;
    }
}
class SolutionCopy2 {
    public int reversePairs(int[] nums) {
        return mergeSoft(nums,0,nums.length-1);
    }
    private int mergeSoft(int[] nums, int left, int right){
        if(left>=right) return 0;
        int mid = (left+right)>>1;
        if(nums[mid]<=nums[mid+1]) return mergeSoft(nums,left,mid)+mergeSoft(nums,mid+1,right);
        return mergeSoft(nums,left,mid)+mergeSoft(nums,mid+1,right)+merge(nums,left,mid,right);
    }
    private int merge(int[] nums,int left,int mid,int right){
        int i = left;
        int j = mid+1;
        int k = 0;
        int count = 0;
        int[] res = new int[right-left+1];
        while(i<=mid&&j<=right){
            if(nums[i]>nums[j]) count += mid-i+1;
            res[k++] = nums[i] <=nums[j] ? nums[i++]:nums[j++];
        }
        while(i<=mid) res[k++] = nums[i++];
        while(j<=right) res[k++] = nums[j++];
        for(int m=0;m<res.length;m++){
            nums[left+m] = res[m];
        }
        return count;
    }
}