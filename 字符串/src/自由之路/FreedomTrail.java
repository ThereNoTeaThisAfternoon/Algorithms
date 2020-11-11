package 自由之路;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileName: FreedomTrail.java
 * 类的详细说明
 * 视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
 * 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 * 最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，
 * 然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * |--您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，
 * |--并且这个字符必须等于字符 key[i] 。
 * |--如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，
 * |--您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.11 - 下午 7:27
 * @label String Depth-firstSearch DynamicProgramming
 */
public class FreedomTrail {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个字符串ring：godding");
        while ((line = in.readLine()) != null) {
            System.out.println("请输入字符都被包含在ring内的字符串key：gd");
            String key = in.readLine();
            int result = new SolutionCopy().findRotateSteps(line, key);
            System.out.println("拼写关键词中所有字符的最少步数为：" + result);
        }
    }
}

class Solution {
    public int findRotateSteps(String ring, String key) {
        int n = ring.length(), m = key.length();
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; ++i) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp[i], 0x3f3f3f);
        }
        for (int i : pos[key.charAt(0) - 'a']) {
            dp[0][i] = Math.min(i, n - i) + 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
        }
        return Arrays.stream(dp[m - 1]).min().getAsInt();
    }
}


/**
 * 深度优先搜索
 */
class SolutionCopy {

    public int findRotateSteps(String ring, String key) {
        List<Integer>[] posList = new ArrayList[26];
        // 初始化list
        for (int i = 0; i < 26; i++) {
            posList[i] = new ArrayList<>();
        }
        for (int i = 0; i < ring.length(); i++) {
            posList[ring.charAt(i) - 'a'].add(i);
        }
        int[][] memory = new int[key.length()][ring.length()];
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < ring.length(); j++) {
                memory[i][j] = -1;
            }
        }
        return dfs(key.toCharArray(), 0, ring.toCharArray(), 0, memory, posList);
    }

    /**
     * 深度优先搜索
     *
     * @param keys
     * @param i     key中下一个搜索的字符
     * @param rings
     * @param j     当前环所在地点
     * @return
     */
    public int dfs(char[] keys, int i, char[] rings, int j, int[][] memory, List<Integer>[] posList) {
        if (i >= keys.length) return 0;
        //向左向右寻找递归调用过程中会出现同样的路径
        if (memory[i][j] != -1) return memory[i][j];
        int min = Integer.MAX_VALUE;
        //向右寻找
        for (int pos : posList[keys[i] - 'a']) {
            if (rings[pos] == keys[i]) {
                int d = Math.abs(pos - j);
                int dis = dfs(keys, i + 1, rings, pos, memory, posList) + Math.min(d, rings.length - d) + 1;
                if (dis < min) min = dis;
            }
        }
        memory[i][j] = min;
        return min;
    }
}