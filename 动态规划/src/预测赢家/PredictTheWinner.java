package 预测赢家;

import Dynamic公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: PredictTheWinner.java
 * 类的详细说明
 * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，
 * 随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。
 * 每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。
 * 直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @label DynamicProgramming Depth-firstSearch
 * @Date 2020.9.1
 */
public class PredictTheWinner {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个数组：[1,5,233,7]");
        while ((line = in.readLine()) != null) {
            int[] nums = new PublicMethod().stringToIntegerArray(line);
            boolean result = new Solution().predictTheWinner(nums);
            System.out.println("先手玩家" + (result ? "能获胜" : "不能获胜"));
        }
    }
}

class Solution {
    public boolean predictTheWinner(int[] nums) {
        if (nums.length % 2 == 0) {//数组长度为偶数，则先手必然获胜
            return true;
        }
        //dp[i][j] 表示当数组剩下的部分为下标 ii 到下标 jj 时，
        // 当前玩家与另一个玩家的分数之差的最大值，注意当前玩家不一定是先手。
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = nums.length - 2; i >= 0; --i) {
            for (int j = i + 1; j < nums.length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }
}

class SolutionCopy {
    public boolean predictTheWinner(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        System.arraycopy(nums, 0, dp, 0, length);
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] >= 0;
    }
}

class SolutionCopy2 {
    //dfs
    public boolean predictTheWinner(int[] nums) {
        return total(nums, 0, nums.length - 1, 1) >= 0;
    }

    public int total(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return nums[start] * turn;
        }
        int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
        int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
        return Math.max(scoreStart * turn, scoreEnd * turn) * turn;
    }
}