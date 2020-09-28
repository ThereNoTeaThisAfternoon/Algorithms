package Tree公共方法;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PublicMethod {
    //IntegerArrayList -> String
    public String integerArrayListToString(List<Integer> nums, int len) {
        if (len == 0)
            return "[]";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            result.append(nums.get(i)).append(", ");
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    //String -> BinaryTree
    public TreeNode stringToBinaryTreeNode(String input) {
        String[] parts = stringToStringArray(input);
        if (parts == null)
            return null;
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            //添加左节点
            if (index == parts.length) break;
            item = parts[index++].trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }
            //添加右节点
            if (index == parts.length) break;
            item = parts[index++].trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    /**
     * 重载了该方法，为每个节点增添一个右侧节点
     */
    public Node stringToBinaryTreeNode(String input, Node empty) {
        String[] parts = stringToStringArray(input);
        if (parts == null) {
            return null;
        }
        String item = parts[0];
        Node root = new Node(Integer.parseInt(item));
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            //添加左节点
            if (index == parts.length) {
                break;
            }
            item = parts[index++];
            if (!"null".equals(item)) {
                Node left = new Node(Integer.parseInt(item));
                node.left = left;
                queue.add(left);
            }
            //添加右节点
            if (index == parts.length) {
                break;
            }
            item = parts[index++];
            if (!"null".equals(item)) {
                Node right = new Node(Integer.parseInt(item));
                node.right = right;
                queue.add(right);
            }
        }
        return root;
    }

    //String -> StringArray
    //"[1,2,3]" -> {"1","2","3"}
    public String[] stringToStringArray(String input) {
        input = input.trim();//除去两旁多余的空格
        input = input.substring(1, input.length() - 1);//除掉左右括号
        if (input.length() == 0)
            return null;
        return input.split(",");//regularExpression
    }

    //String -> IntegerArray
    //[3,9,20,15,7]
    public int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() < 1)
            return new int[0];
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        int index = 0;
        for (String string : parts) {
            output[index++] = Integer.parseInt(string);
        }
        return output;
    }

    //BinaryTree -> String(层序遍历)
    public String integerTreeToString(TreeNode root) {
        StringBuilder sB = new StringBuilder();
        List<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode node = list.remove(0);
            if (node != null)
                sB.append(node.val).append(", ");
            else {
                sB.append("null, ");
                continue;
            }
            if (node.left != null)
                list.add(node.left);
            else
                list.add(null);

            if (node.right != null)
                list.add(node.right);
            else
                list.add(null);
        }
        return "[" + sB.toString().substring(0, sB.length() - 2) + "]";
    }

    //BinaryTree -> String 先序遍历
    public String integerTreeString(TreeNode root) {
        if (root == null)
            return "";
        return root.val + " " + integerTreeString(root.left) + integerTreeString(root.right);
    }

    //BinaryTree -> String 中序遍历
    public String integerTreeToInorderString(TreeNode root) {
        if (root == null) {
            return "";
        }
        return integerTreeToInorderString(root.left) + root.val + " " + integerTreeToInorderString(root.right);
    }
}
