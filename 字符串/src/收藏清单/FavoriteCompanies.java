package 收藏清单;
/**
 * FileName: FavoriteCompanies.java
 * 类的详细说明
 * 给你一个数组 favoriteCompanies ，其中 favoriteCompanies[i] 是第 i 名用户收藏的公司清单（下标从 0 开始）。
 * 请找出不是其他任何人收藏的公司清单的子集的收藏清单，并返回该清单下标。下标需要按升序排列。
 *
 * @label String Sort
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.22
 * @version 1.00
 */

import String公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FavoriteCompanies {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入收藏的公司清单：[[\"leetcode\",\"google\",\"facebook\"],[\"google\",\"microsoft\"],[\"google\",\"facebook\"],[\"google\"],[\"amazon\"]]");
        while ((line = in.readLine()) != null) {
            List<List<String>> favoriteCompanies = new PublicMethod().stringToString2dList(line);
            List<Integer> ret = new Solution().peopleIndexes(favoriteCompanies);
            String out = new PublicMethod().integerListToString(ret);
            System.out.println("非子集收藏清单为：" + out);
        }
    }
}

class Solution {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> list = new ArrayList<>();
        int len = favoriteCompanies.size();
        Map<Integer, Set<List<String>>> map = new HashMap<>();
        for (int i = 0; i < len; i++)
            map.put(i, new HashSet(favoriteCompanies.get(i)));
        for (int i = 0; i < len; i++) {
            boolean flag = true;
            for (int j = 0; j < len; j++) {
                if (i == j)
                    continue;
                if (map.get(j).containsAll(map.get(i))) {
                    flag = false;
                    break;
                }
            }
            if (flag) list.add(i);
        }
        return list;
    }
}