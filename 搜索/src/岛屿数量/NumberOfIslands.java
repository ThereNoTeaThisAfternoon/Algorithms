package 岛屿数量;
/**
 * FileName: NumberOfIslands.java
 * 类的详细使用说明
 * 由 '0'(海洋) '1'(陆地)组成二维网格，请计算网格中 岛屿 的数量
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，假设该网格的四条边均被水包围。
 * input -> 1001 0010 1100 0001
 *
 * @label Depth-firstSearch Breadth-firstSearch UnionFind
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.20
 * @version 1.00
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            char[][] input = stringToChar2dArray(line);
            int result = new SolutionCopy1().numIslands(input);
            System.out.println("岛屿的个数是：" + result);
        }
    }

    public static char[][] stringToChar2dArray(String input) {
        if (input.length() == 0) return new char[0][];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            if (temp == '0' || temp == '1') {
                sb.append(temp);
            }
        }
        int len = (int) Math.sqrt(sb.length());
        int step = 0;
        char[][] output = new char[len][len];

        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                if (step < sb.length()) {
                    output[i][j] = sb.charAt(step++);
                }
            }
        }
        return output;
    }
}

class Solution {
    //DFS
    /**
     * MethodName: numIsLands
     * 遍历岛这个二维数组，如果当前数为1，则进入感染函数并将岛个数+1
     * 感染函数：将所有相连的1都标注成2，避免遍历过程中重复计数
     *
     * @param grid char[][]
     * @return island
     */
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid == null) return 0;
        int islandNum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
                if (grid[i][j] == '1') {
                    infect(grid, i, j);
                    islandNum++;
                }
            }
        }
        return islandNum;
    }

    //感染函数
    public void infect(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        infect(grid, i, j - 1);
        infect(grid, i + 1, j);
        infect(grid, i, j + 1);
        infect(grid, i - 1, j);
    }
}

class SolutionCopy1 {
    //BFS
    //扫描整个二维网格。如果一个位置为 1，则将其加入队列，开始进行广度优先搜索。
    //在广度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。直到队列为空，搜索结束。
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int row = grid.length;
        int column = grid[0].length;
        int islandNum = 0;
        for(int i=0;i<row;++i){
            for(int j=0;j<column;++j){
                if(grid[i][j] == '1'){
                    ++islandNum;
                    grid[i][j] = '0';
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(i*column+j);
                    while(!neighbors.isEmpty()){
                        int id = neighbors.remove();
                        int r = id/column;
                        int c = id%column;
                        if(r-1>=0 &&grid[r-1][c] == '1'){
                            neighbors.add((r-1)*column+c);
                            grid[r-1][c] = '0';
                        }
                        if(r+1<row && grid[r+1][c] == '1'){
                            neighbors.add((r+1)*column+c);
                            grid[r+1][c] = '0';
                        }
                        if(c-1>=0&& grid[r][c-1] == '1'){
                            neighbors.add(r*column+c-1);
                            grid[r][c-1] = '0';
                        }
                        if(c+1<column&& grid[r][c+1] == '1'){
                            neighbors.add(r*column+c+1);
                            grid[r][c+1] = '0';
                        }
                    }
                }
            }
        }
        return islandNum;
    }
}