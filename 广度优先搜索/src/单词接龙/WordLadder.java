package 单词接龙;

import BFS公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * FileName: WordLadder.java
 * 类的详细说明
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * <p>
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.11.5 - 下午 9:26
 * @label Breath-firstSearch
 */
public class WordLadder {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String beginWord;
        System.out.println("请输入beginWord：hit");
        while ((beginWord = in.readLine()) != null) {
            System.out.println("请输入endWord：cog");
            String endWord = in.readLine();
            System.out.println("请输入一个字典wordList：[hot,dot,dog,lot,log,cog]");
            List<String> wordList = PublicMethod.stringToStringList(in.readLine());
            int result = new Solution().ladderLength(beginWord, endWord, wordList);
            System.out.println("最短转换序列的长度为：" + result);
        }
    }
}

/**
 * hit -> hot -> dot -↓> lot
 * hit -> hot -> dot -> dog -↓> log
 * hit -> hot -> dot -> dog -> cog
 */
class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 队列保存当前单词能转换的单词
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        // 标记哪些单词被使用过
        boolean[] visited = new boolean[wordList.size()];
        int count = 1;// 转换次数，转换层数

        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            while (size-- > 0) {
                String cur = queue.remove();
                for (int i = 0; i < wordList.size(); i++) {
                    if (visited[i]) {
                        continue;
                    }
                    String dir = wordList.get(i);
                    // 当前层单词转换为下一层的单词，是否为目标单词，否加入队列，标记已访问
                    if (canChange(cur, dir)) {
                        if (dir.equals(endWord)) {
                            return count;
                        }
                        queue.add(dir);
                        visited[i] = true;
                    }
                }
            }
        }
        return 0;
    }

    // 判断当前单词能否转换为下一个单词
    private boolean canChange(String cur, String dir) {
        int diff = 0;
        for (int i = 0; i < cur.length(); i++) {
            if (diff > 1) {
                break;
            }
            if (cur.charAt(i) != dir.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }

}
