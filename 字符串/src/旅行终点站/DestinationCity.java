package 旅行终点站;
/**
 * FileName: DestinationCity.java
 * 类的详细说明
 * 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，
 * 其中 paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi 直接前往 cityBi 。
 * 请找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
 * 题目数据保证线路图会形成一条不存在循环的线路，因此只会有一个旅行终点站。
 *
 * @label String
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.3
 * @version 1.00
 */

import String公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DestinationCity {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个旅游路线图：[[\"p1\",\"p2\"],[\"p2\",\"p3\"],[\"p3\",\"p4\"]]");
        while ((line = in.readLine()) != null) {
            List<List<String>> paths = new PublicMethod().stringToString2dList(line);
            String ret = new Solution().destCity(paths);
            System.out.println("你最终能到达的目的地为："+ret);
        }
    }
}

class Solution {
    /**
     * @param paths [["p1","p2"],["p2","p3"],["p3","p4"]]
     * @return p4
     */
    //将二维列表注入到map中,如果存在key, 则将value给到key,直到找到res
    public String destCity(List<List<String>> paths) {
        if (paths == null)
            return "";
        Map<String, String> map = new HashMap<>();
        for (List<String> path : paths) {
            map.put(path.get(0), path.get(1));
        }
        String key = paths.get(0).get(0);
        while (map.containsKey(key)) {
            key = map.get(key);
        }
        return key;
    }
}