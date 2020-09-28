package 填充每个节点的下一个右侧节点指针Ⅱ;

import Tree公共方法.Node;
import Tree公共方法.PublicMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * FileName: PopulatingNextRightPointersInEachNodeII.java
 * 类的详细说明
 * 给定一个二叉树，填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.9.28 - 下午 8:18
 * @label Tree Breadth-firstSearch Depth-firstSearch
 */
public class PopulatingNextRightPointersInEachNodeII {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一棵二叉树：[1,2,3,4,5,null,7]");
        while ((line = in.readLine()) != null) {
            Node root = new PublicMethod().stringToBinaryTreeNode(line, null);
            Node result = new Solution().connect(root);
            System.out.println("这玩意自己调试着看答案吧。。。" + result);
        }
    }
}

class Solution {
    //使用队列完成层次遍历
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node node = queue.remove();
                node.next = queue.peek();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return root;
    }
}

class SolutionCopy {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null && root.right != null) {
            root.left.next = root.right;
        }
        if (root.left != null && root.right == null) {
            root.left.next = getNext(root.next);
        }
        if (root.right != null) {
            root.right.next = getNext(root.next);
        }
        connect(root.right);
        connect(root.left);
        return root;
    }

    public Node getNext(Node root) {
        if (root == null) return null;
        if (root.left != null) return root.left;
        if (root.right != null) return root.right;
        if (root.next != null) return getNext(root.next);
        return null;
    }
}