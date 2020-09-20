package 另一个树的子树;
/**
 * FileName: SubtreeOfAnotherTree.java
 * 类的详细说明
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
 * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * @label Tree
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.7
 * @version 1.00
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubtreeOfAnotherTree {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入树s：[2,4,3,1,2,null,null,0]");
        while ((line = in.readLine()) != null) {
            TreeNode s = new PublicMethod().stringToBinaryTreeNode(line);
            System.out.println("请输入要匹配的子树：[4,1,2]");
            line = in.readLine();
            TreeNode t = new PublicMethod().stringToBinaryTreeNode(line);
            boolean ret = new Solution().isSubtree(s, t);
            String out = String.valueOf(ret);
            System.out.println(out);
        }
    }
}

class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t != null || s != null && t == null)
            return false;
        return subForm(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean subForm(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return subForm(s.left, t.left) && subForm(s.right, t.right);
    }
}
