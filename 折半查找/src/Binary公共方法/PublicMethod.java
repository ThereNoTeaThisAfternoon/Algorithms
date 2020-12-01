package Binary公共方法;

public class PublicMethod {
    //String -> IntegerArray [1,2,3]
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new int[0];
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        int index = 0;
        for (String part : parts)
            output[index++] = Integer.parseInt(part);
        return output;
    }

    /**
     * String:"[[1,5,9],[10,11,13],[12,13,15]]" -> int[][]
     */
    public static int[][] stringTo2DIntegerArray(String input) {
        input = input.trim(); // 去除两旁多余空格
        input = input.substring(1, input.length() - 1); // 去掉左右括号
        if (input.length() == 0) {
            return new int[0][];
        }
        // 获取行数、列数
        int rows = 0, cols = 1;
        boolean button = true;
        for (char ch : input.toCharArray()) {
            if (ch == ']') {
                rows++;
                button = false;
            } else if (button && ch == ',') {
                cols += 1;
            }
        }
        int[][] out = new int[rows][cols];
        int i = 0;
        StringBuilder str = new StringBuilder();
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b < cols; b++) {
                while (i < input.length() && !Character.isDigit(input.charAt(i)))
                    i++;
                while (i < input.length() && Character.isDigit(input.charAt(i))) {
                    str.append(input.charAt(i++));
                }
                out[a][b] = Integer.parseInt(str.toString());
                str = new StringBuilder();
            }
        }
        return out;
    }

    /**
     * int[][]:{{1,2},{1,2}} -> String
     */
    public static String integer2DArrayToString(int[][] input) {
        StringBuilder output = new StringBuilder("[");
        for (int[] arr : input) {
            StringBuilder sb = new StringBuilder("[");
            for (int elem : arr) {
                sb.append(elem).append(",");
            }
            output.append(sb.substring(0, sb.length() - 1)).append("], ");
        }
        return output.substring(0, output.length() - 2) + "]";
    }

}
