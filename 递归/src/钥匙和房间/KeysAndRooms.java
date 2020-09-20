package 钥匙和房间;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * FileName: KeysAndRooms.java
 * 类的详细说明
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1,
 * and each room may have some keys to access the next room. 
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @label Depth-firstSearch Graph
 * @Date 2020.8.31
 */
public class KeysAndRooms {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个房间列表：[[1],[2],[3],[]]");
        while ((line = in.readLine()) != null) {
            List<List<Integer>> rooms = stringTo2DList(line);
            boolean result = new Solution().canVisitAllRooms(rooms);
            System.out.println("是否可以访问到所有房间：" + (result ? "可以" : "不能"));
        }
    }

    private static List<List<Integer>> stringTo2DList(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }
        char[] letters = input.toCharArray();
        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> item = null;
        for (char letter : letters) {
            if (letter == '[') {
                if (item != null) {
                    rooms.add(item);
                }
                item = new ArrayList<>();
            } else if (item != null && letter >= '0' && letter <= '9') {
                item.add(Integer.parseInt(String.valueOf(letter)));
            }
        }
        rooms.add(item);
        return rooms;
    }
}

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0) {
            return true;
        }
        boolean[] visited = new boolean[rooms.size()];
        Set<Integer> keys = new HashSet<>();
        keys.add(0);
        dfs(rooms, visited, keys, 0);
        return keys.size() == rooms.size();
    }

    private void dfs(List<List<Integer>> rooms, boolean[] visited, Set<Integer> keys, int index) {
        if (!visited[index]) {
            visited[index] = true;
            List<Integer> nextKeys = rooms.get(index);
            for (Integer nextKey : nextKeys) {
                keys.add(nextKey);
                dfs(rooms, visited, keys, nextKey);
            }
        }
    }
}