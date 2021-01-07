package 省份数量;

import DFS公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FileName: NumberOfProvinces.java
 * 类的详细说明
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，
 * 且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
 * 而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2021.1.7 - 下午 8:07
 * @label Depth-firstSearch
 */
public class NumberOfProvinces {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个二维矩阵isConnected：[[1,1,0],[1,1,0],[0,0,1]]");
        while ((line = in.readLine()) != null) {
            int[][] isConnected = PublicMethod.stringTo2DIntegerArray(line);
            int result = new Solution().finCircleNum(isConnected);
            System.out.println("矩阵中省份数量为：" + result);
        }
    }
}

class Solution {
    // 使用一个visited数组, 依次判断每个节点, 如果其未访问, 朋友圈数加1并对该节点进行dfs搜索标记所有访问到的节点
    public int finCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int count = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int i) {
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, j);
            }
        }
    }

}