package ListNode公共方法;

public class PublicMethod {
    //String -> ListNode
    public static ListNode stringToListNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return null;
        String[] strings = input.split(",");
        ListNode head = new ListNode(0);
        ListNode dummy = head;
        for (String s : strings) {
            dummy.next = new ListNode(Integer.parseInt(s));
            dummy = dummy.next;
        }
        return head.next;
    }

    //String -> 2DListNode
    public static ListNode[] stringToInteger2dListNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new ListNode[0];
        StringBuilder strB = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ',') {
                continue;
            }
            strB.append(input.charAt(i));
        }
        input = strB.toString();
        String[] strings = input.split("");
        int len = 0;
        for (String string : strings) {
            if (string.equals("]")) {
                len++;
            }
        }
        ListNode[] nodes = new ListNode[len];
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            if (string.equals("]") && len != 0) {
                nodes[--len] = stringBuilderToListNode(sb);
                sb = new StringBuilder();
            }
            if (string.equals("[") || string.equals("]")) {
                continue;
            }
            sb.append(string);
        }
        return nodes;
    }

    private static ListNode stringBuilderToListNode(StringBuilder sb) {
        String string = sb.toString();
        ListNode head = new ListNode(0);
        ListNode dummy = head;
        for (int i = 0; i < string.length(); i++) {
            dummy.next = new ListNode(Integer.parseInt(string.substring(i, i + 1)));
            dummy = dummy.next;
        }
        return head.next;
    }

    //ListNode -> String
    public static String listNodeToString(ListNode node) {
        StringBuilder result = new StringBuilder();
        while (node != null) {
            result.append(node.val).append(", ");
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
}
