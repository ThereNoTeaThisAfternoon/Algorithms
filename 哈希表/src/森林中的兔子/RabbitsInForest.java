package 森林中的兔子;

import HashTable公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName: RabbitsInForest.java
 * 类的详细说明
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。
 * 我们将这些回答放在 answers 数组里。
 * 返回森林中兔子的最少数量。
 * <p>
 * answers 的长度最大为1000。
 * answers[i] 是在 [0, 999] 范围内的整数。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.4.4 - 下午 1:16
 * @label HashTable Math
 */
public class RabbitsInForest {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个整型数组answers：[0,1,1,1,2,2,3,4,5]");
        while ((line = in.readLine()) != null) {
            int[] answers = PublicMethod.stringToIntegerArray(line);
            int result = new SolutionCopy2().numRabbits(answers);
            System.out.println("森林中的兔子有：" + result + "只");
        }
    }
}

class Solution {

    public int numRabbits(int[] answers) {
        int len = answers.length;
        if (len < 1) {
            return 0;
        }
        Map<Integer, Integer> hash = new HashMap<>();
        for (int key : answers) { // key 为兔子的回答(相同兔子数)，val为同样的回答次数
            hash.put(key + 1, hash.getOrDefault(key + 1, 0) + 1);
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            int key = entry.getKey();
            int val = entry.getValue();
            // 3次回答为有2只相同兔子(一只不同兔子)，则实际有4只兔子。
            sum += (val + key - 1) / key * key; //
        }
        return sum;
    }
}

class SolutionCopy {

    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int num = 0;
        for (int answer : answers) {
            if (map.containsKey(answer) && map.get(answer) > 0) {
                map.put(answer, map.get(answer) - 1);
            } else {
                num += answer + 1;
                map.put(answer, answer);
            }
        }
        return num;
    }
}

class SolutionCopy2 {
    public int numRabbits(int[] answers) {
        // count[i]: 已采访的兔子中，回答一致的兔子的回答数+1(即相同兔子数)。
        int[] count = new int[1000 + 1];
        for (int others : answers) {
            count[others + 1]++;
        }
        int ans = 0;
        for (int i = 1; i < count.length; i++) {
            ans += (count[i] + i - 1) / i * i;
        }
        return ans;
    }
}