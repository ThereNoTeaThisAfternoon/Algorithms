package 括号生成;

/**
 * FileName: GenerateParentheses.java
 * 类的详细说明
 * 数字 n 代表生成括号的对数，
 * 设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * @label String Backtracking(回溯)
 * @author &#x8c2f;&#x535a;
 * @Date 2020.4.10
 * @version 1.00
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static String stringListToString(List<String> stringList) {
        StringBuilder sb = new StringBuilder("[");
        for (String item : stringList) {
            sb.append(item);
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            List<String> ret = new Solution().generateParenthesis(n);

            String out = stringListToString(ret);

            System.out.print(out);
        }
    }
}
class Solution{
    /**
     * MethodName: generateParentheses
     * 类方法详细使用说明
     * 如果左括号数量不大于 n，放一个左括号。
     * 如果右括号数量小于左括号的数量，放一个右括号。
     *
     * @param n int 括号对数
     * @param answer string 将存入列表的值
     * @param left 左括号
     * @param right 右括号
     * @return 返回结果
     * @throws
     */
    public List<String> result = new ArrayList<>();
    public List<String> generateParenthesis(int n){
        dfs(new String(),n,n);
        return result;
    }
    private void dfs(String answer,int left,int right){
        if(left == 0 && right == 0){//左右括号都不剩余，递归终止
            result.add(answer);
            return;
        }
        if(left > 0){//左括号剩余拼接左括号
            dfs(answer+"(",left - 1,right);
        }
        if(left < right){//右括号多于左，拼接右括号
            dfs(answer+")",left,right-1);
        }
    }
}