package K连续位最小翻转次数;

import Greedy公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: MinimumNumberOfKConsecutiveBitFlips.java
 * 类的详细说明
 * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，
 * 同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
 * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
 * <p>
 * 1 <= A.length <= 30000
 * 1 <= K <= A.length
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.2.18 - 下午 7:01
 * @label GreedyAlgorithm SlidingWindow
 */
public class MinimumNumberOfKConsecutiveBitFlips {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组nums：[0,0,0,1,0,1,1,0]");
        while ((line = in.readLine()) != null) {
            int[] nums = PublicMethod.stringToIntegerArray(line);
            System.out.println("请输入所需要翻转的k：3");
            int k = Integer.parseInt(in.readLine());
            int result = new Solution().minKBitFlips(nums, k);
            System.out.println("K位翻转最小次数为：" + result);
        }
    }
}

/**
 * GreedyAlgorithm
 * 目标是将数组的每一位都变为 1 ，因此对于每一位 0 都需要翻转。
 * 我们可以从前往后处理，遇到 0 则对后面的 k 位进行翻转。
 * 这样我们的算法复杂度是 O(nk) 的，数据范围是 3w（数量级为 10^4），极限数据下单秒的运算量在 10^8以上，会有超时风险。
 */
class Solution {

    public int minKBitFlips(int[] nums, int k) {
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len; ++i) {
            if (nums[i] == 0) {
                if (i + k > len) {
                    return -1;
                }
                for (int j = i; j < i + k; ++j) {
                    nums[j] ^= 1;
                }
                ans++;
            }
        }
        return ans;
    }
}

class SolutionCopy {
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            revCnt += diff[i];
            if ((A[i] + revCnt) % 2 == 0) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                ++revCnt;
                --diff[i + K];
            }
        }
        return ans;
    }
}

class SolutionCopy2 {
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            if (i >= K && A[i - K] > 1) {
                revCnt ^= 1;
                A[i - K] -= 2; // 复原数组元素，若允许修改数组 A，则可以省略
            }
            if (A[i] == revCnt) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                revCnt ^= 1;
                A[i] += 2;
            }
        }
        return ans;
    }
}
