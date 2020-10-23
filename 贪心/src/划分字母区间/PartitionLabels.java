package 划分字母区间;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: PartitionLabels.java
 * 类的详细说明
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.10.23 - 下午 3:31
 * @label GreedyAlgorithm
 */
public class PartitionLabels {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个仅由小写字母组成的字符串S：ababcbacadefegdehijhklij");
        while ((line = in.readLine()) != null) {
            List<Integer> result = new Solution().partitionLabels(line);
            result.forEach(System.out::println);
        }
    }
}

class Solution {
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        int len = S.length();
        // 获取每一个字符在字符串中最后一次出现的位置
        for (int i = 0; i < len; i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<>();
        int start = 0, end = 0;
        // 获取区间
        for (int i = 0; i < len; i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }
}