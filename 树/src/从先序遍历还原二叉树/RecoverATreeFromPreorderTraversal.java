package 从先序遍历还原二叉树;
/**
 * FileName: RecoverATreeFromPreorderTraversal.java
 * 类的详细说明
 * 从二叉树的根节点 root 开始进行深度优先搜索。
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。
 * （如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 * 给出遍历输出 S，还原树并返回其根节点 root。
 *
 * @label Tree Depth-firstSearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.6.18
 * @version 1.00
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class RecoverATreeFromPreorderTraversal {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("输入一个字符串进行先序遍历生成树：1-2--3--4-5--6--7");
        while ((line = in.readLine()) != null) {
            TreeNode result = new Solution().recoverFromPreorder(line);
            String out = new PublicMethod().integerTreeString(result);
            System.out.println("先序遍历结果为：[" + out.substring(0, out.length() - 2) + "]");
        }
    }
}

class Solution {

    int pos = 0;

    public TreeNode recoverFromPreorder(String S) {
        return recoverHelper(S, 0);
    }

    //先序遍历添加
    private TreeNode recoverHelper(String s, int level) {
        for (int i = 0; i < level; i++)
            if (pos + i >= s.length() || s.charAt(pos + i) != '-')
                return null;
        pos += level;
        int num = 0;
        //获取当前节点的整数
        while (Character.isDigit(s.charAt(pos))) {
            num += num * 10 + (s.charAt(pos++) - '0');
            if (pos == s.length())
                break;
        }
        TreeNode root = new TreeNode(num);
        root.left = recoverHelper(s, level + 1);
        root.right = recoverHelper(s, level + 1);
        return root;
    }
}

class SolutionCopy1 {
    //迭代
    public TreeNode recoverFromPreorder(String S) {
        Deque<TreeNode> path = new LinkedList<>();
        int pos = 0;
        while (pos < S.length()) {
            int level = 0;
            while (S.charAt(pos) == '-') {
                ++level;
                ++pos;
            }
            int value = 0;
            while (pos < S.length() && Character.isDigit(S.charAt(pos))) {
                value = value * 10 + (S.charAt(pos) - '0');
                ++pos;
            }
            TreeNode node = new TreeNode(value);
            if (level == path.size()) {
                if (!path.isEmpty()) {
                    path.peek().left = node;
                }
            } else {
                while (level != path.size()) {
                    path.pop();
                }
                path.peek().right = node;
            }
            path.push(node);
        }
        while (path.size() > 1) {
            path.pop();
        }
        return path.peek();
    }
}