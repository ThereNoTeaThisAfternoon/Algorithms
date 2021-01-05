package 较大分组的位置;

import Array公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: PositionsOfLargeGroups.java
 * 类的详细说明
 * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
 * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。
 * 上例中的 "xxxx" 分组用区间表示为 [3,6] 。
 * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
 * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
 * <p>
 * 1 <= s.length <= 1000
 * s 仅含小写英文字母
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.5 - 下午 6:48
 * @label Array
 */
public class PositionsOfLargeGroups {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个仅包含小写字符的字符串s：abbxxxxzzy");
        while ((line = in.readLine()) != null) {
            List<List<Integer>> result = new Solution().largeGroupPositions(line);
            String out = PublicMethod.integer2DListToString(result);
            System.out.println(out);
        }
    }

}

class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        // 给原来的字符串拼一个大写字母到最后，就不用特殊处理连续字母在最后的情况了
        s = s + 'A';
        List<List<Integer>> lists = new ArrayList<>(); // 用以存放较大分组的头尾位置
        int begin = 0; // 每个分组的头位置
        int len = s.length();
        for (int i = 1; i < len; ++i) {
            // 当前后两个字符不相同时，说明分组改变了
            if (s.charAt(i) != s.charAt(i - 1)) {
                // 一个分组的长度必须大于等于3，才能存放该分组
                if (i - begin >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(begin);
                    list.add(i - 1);
                    lists.add(list);
                }
                begin = i;
            }
        }
        return lists;
    }
}