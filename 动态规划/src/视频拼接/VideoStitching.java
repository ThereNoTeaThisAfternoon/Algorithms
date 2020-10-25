package 视频拼接;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * FileName: VideoStitching.java
 * 类的详细说明
 * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。
 * 可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 * 需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。
 * 返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.24 - 上午 10:53
 * @label DynamicProgramming
 */
public class VideoStitching {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个视频片段：[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]");
        while ((line = in.readLine()) != null) {
            //int[][] clips = new PublicMethod().stringTo2DIntegerArray(line);
            int[][] clips = {
                    {0, 2},
                    {4, 6},
                    {8, 10},
                    {1, 9},
                    {1, 5},
                    {5, 9}
            };
            System.out.println("请输入需要的持续时长T：10");
            int T = Integer.parseInt(in.readLine());
            int result = new SolutionCopy().videoStitching(clips, T);
            System.out.println("结果为：" + result);
        }
    }
}

/**
 * DynamicProgramming
 */
class Solution {
    public int videoStitching(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                if (clip[0] < i && i <= clip[1]) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
    }
}


/**
 * 先找出最后视频片段，然后根据此值创建数组，数组下标表示片段开始，值表示此开始最长达到的片段结尾，
 * 然后根据贪心思想从0开始遍历，每次从上次遍历结尾查询到上次遍历最大值并更新最大值，
 * 直到最大值达到或超过T，若某次遍历结束结尾未发生更新，则返回 -1
 */
class SolutionCopy {
    public int videoStitching(int[][] clips, int T) {
        int[] maxn = new int[T];
        int last = 0, ret = 0, pre = 0;
        for (int[] clip : clips) {
            if (clip[0] < T) {
                maxn[clip[0]] = Math.max(maxn[clip[0]], clip[1]);
            }
        }
        for (int i = 0; i < T; i++) {
            last = Math.max(last, maxn[i]);
            if (i == last) {
                return -1;
            }
            if (i == pre) {
                ret++;
                pre = last;
            }
        }
        return ret;
    }
}