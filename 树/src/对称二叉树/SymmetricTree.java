package 对称二叉树;
/**
 * FileName: SymmetricTree.java
 * 类的详细说明
 * 给定一个二叉树，检查它是否是 镜像对称。
 *
 * @label Tree Depth-firstSearch Breadth-firstSearch
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @Date 2020.5.30
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一颗BinaryTree：[1,2,2,3,4,4,3]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            boolean result = new Solution().isSymmetric(root);
            String out = result ? "true" : "false";
            System.out.println(out);
        }
    }
}

class Solution {
    Queue<TreeNode> queue = new LinkedList<>();

    //BFS
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode l = queue.poll();
            TreeNode r = queue.poll();
            if (l == null && r == null)
                continue;
            if (l == null || r == null || l.val != r.val)
                return false;
            queue.offer(l.left);
            queue.offer(r.right);
            queue.offer(l.right);
            queue.offer(r.left);
        }
        return true;
    }
}

class SolutionCopy {
    //DFS
    public boolean isSymmetric(TreeNode root) {
        return isSym(root.left, root.right);
    }

    private boolean isSym(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return isSym(p.left, q.right) && isSym(p.right, q.left);
    }
}