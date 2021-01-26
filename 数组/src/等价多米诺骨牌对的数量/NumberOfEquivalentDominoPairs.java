package 等价多米诺骨牌对的数量;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: NumberOfEquivalentDominoesPairs.java
 * 类的详细说明
 * 给你一个由一些多米诺骨牌组成的列表 dominoes。
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 * <p>
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.26 - 下午 6:58
 * @label Array
 */
public class NumberOfEquivalentDominoPairs {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二维数组dominoes：[[1,2],[2,1],[3,4],[5,6]]");
        while ((line = in.readLine()) != null) {
            int[][] dominoes = PublicMethod.stringTo2DIntegerArray(line);
            int result = new Solution().numEquivDominoPairs(dominoes);
            System.out.println("等价的骨牌对的数量为：" + result);
        }
    }
}

/**
 * Enum O(n2)O(1)
 */
class Solution {

    public int numEquivDominoPairs(int[][] dominoes) {
        int ret = 0;
        for (int i = 0; i < dominoes.length - 1; i++) {
            for (int j = i + 1; j < dominoes.length; j++) {
                if (dominoes[i][0] == dominoes[j][0] && dominoes[i][1] == dominoes[j][1]) {
                    ret++;
                } else if (dominoes[i][0] == dominoes[j][1] && dominoes[i][1] == dominoes[j][0]) {
                    ret++;
                }
            }
        }
        return ret;
    }
}

/**
 * 让每一个二元对都变为指定的格式，即第一维必须不大于第二维。
 * 这样两个二元对「等价」当且仅当两个二元对完全相同。
 * 注意到二元对中的元素均不大于 99，因此我们可以将每一个二元对拼接成一个两位的正整数，
 * 即 (x,y) -> 10x+y || (x,y) -> 10x+y。
 * 这样就无需使用哈希表统计元素数量，而直接使用长度为 100 的数组即可。
 */
class SolutionCopy {

    public int numEquivDominoPairs(int[][] dominoes) {
        int[] num = new int[100];
        int ret = 0;
        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }
}