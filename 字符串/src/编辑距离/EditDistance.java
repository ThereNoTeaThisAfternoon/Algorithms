package 编辑距离;

/**
 * FileName: EditDistance.java
 * 类的详细说明
 * 从Terminal输入两个单词 word1 和 word2
 * 计算出将 word1 转换成 word2 所使用的最少操作数
 * 对一个单词进行如下三种操作
 * 插入一个字符、删除一个字符、替换一个字符
 *
 * @label   String DynamicProgramming(动态规划)
 * @author  &#x8c2f;&#x535a;
 * @Date    2020.4.9
 * @version 1.00
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditDistance {

    public static String stringToString(String input) {
        if (input == null) {
            return "null";
        }
        return input;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String word1 = stringToString(line);
            line = in.readLine();
            String word2 = stringToString(line);

            int ret = new Solution().minDistance(word1, word2);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
class Solution {
    /**
     * MethodName: minDistance
     * 类方法详细使用说明
     * 1、状态定义：
     * dp[i][j]表示word1的前i个字母转换成word2的前j个字母所使用的最少操作。
     * 2、状态转移：
     * i指向word1，j指向word2
     * 若当前字母相同，则dp[i][j] = dp[i - 1][j - 1];
     * 否则取增删替三个操作的最小值 + 1， 即:
     * dp[i][j] = min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]) + 1。
     *
     * @param word1 string
     * @param word2 string
     * @return 返回结果的说明
     * @throws
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(),len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i=0;i<=len1;++i){
            dp[i][0] = i;
        }
        for(int j=0;j<=len2;j++){
            dp[0][j] = j;
        }
        for(int a = 1;a<=len1;++a){
            for(int b = 1;b<=len2;b++){
                if(word1.charAt(a-1) == word2.charAt(b-1)){
                    dp[a][b] = dp[a-1][b-1];
                }else{
                    dp[a][b] = 1 +Math.min(Math.min(dp[a-1][b],dp[a][b-1]),dp[a-1][b-1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
