package 分发饼干;

import Greedy公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: AssignCookies.java
 * 类的详细说明
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
 * 并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，
 * 我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * <p>
 * 1 <= g.length <= 3 * 104
 * 0 <= s.length <= 3 * 104
 * 1 <= g[i], s[j] <= 231 - 1
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.25 - 下午 9:35
 * @label GreedyAlgorithm
 */
public class AssignCookies {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("孩子的胃口值为g：[3,2,1]");
        while ((line = in.readLine()) != null) {
            int[] g = PublicMethod.stringToIntegerArray(line);
            System.out.println("每块饼干的尺寸为s：[1,1]");
            int[] s = PublicMethod.stringToIntegerArray(in.readLine());
            int result = new Solution().findContentChildren(g, s);
            System.out.println("最多能满足的孩子数量为：" + result);
        }
    }
}

class Solution {

    public int findContentChildren(int[] g, int[] s) {
        int child = 0, cookie = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        while (child < g.length && cookie < s.length) {
            if (g[child] <= s[cookie]) {
                child++;
            }
            cookie++;
        }
        return child;
    }
}
