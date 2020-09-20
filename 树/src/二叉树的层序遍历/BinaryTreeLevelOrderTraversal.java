package 二叉树的层序遍历;
/**
 * FileName: BinaryTreeLevelOrderTraversal.java
 * 类的详细说明
 * 给一个二叉树，返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * @label Tree Breath-firstSearch
 * @author &#x8c2f;&#x535a;
 * @Date 2020.5.13
 * @version 1.00
 */

import Tree公共方法.PublicMethod;
import Tree公共方法.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个列表表示 a binary tree：[3,9,20,null,null,15,7]");
        while ((line = in.readLine()) != null) {
            TreeNode root = new PublicMethod().stringToBinaryTreeNode(line);
            List<List<Integer>> lists = new Solution().leverOrder(root);
            StringBuilder string = new StringBuilder();
            for (List<Integer> list : lists) {
                StringBuilder sB = new StringBuilder();
                for (Integer l : list) {
                    sB.append(l).append(", ");
                }
                string.append("[").append(sB.toString(), 0, sB.length() - 2).append("], ");
            }

            String out = string.toString();
            out = "[" + out.substring(0, out.length()-2) + "]";
            System.out.println(out);
        }
    }
}

class Solution {

    public List<List<Integer>> leverOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            while (count > 0) {
                TreeNode node = queue.poll();//返回和删除queue的head
                list.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
                count--;
            }
            ans.add(list);
        }
        return ans;
    }
}