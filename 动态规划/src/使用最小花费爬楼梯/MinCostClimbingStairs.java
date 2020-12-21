package 使用最小花费爬楼梯;

import Dynamic公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: MinCostClimbingStairs.java
 * 类的详细说明
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 * 每个阶梯都有一定数量坨屎，一次只能跨一个或者两个阶梯，
 * 走到一个阶梯就要吃光上面的屎，问怎么走才能吃最少的屎？
 * 开局你选前两个阶梯的其中一个作为开头点，并吃光该阶梯的屎。
 * <p>
 * cost 的长度将会在 [2, 1000]。
 * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.21 - 下午 8:23
 * @label DynamicProgramming Array
 */
public class MinCostClimbingStairs {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组cost：[1,2,10,1]");
        while ((line = in.readLine()) != null) {
            int[] cost = new PublicMethod().stringToIntegerArray(line);
            int result = new Solution().minCostClimbingStairs(cost);
            System.out.println("最小体力花费值为：" + result);
        }
    }
}

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        for (int i = 2; i < cost.length; ++i) {
            cost[i] = Math.min(cost[i - 1] + cost[i], cost[i - 2] + cost[i]);
        }
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }
}
