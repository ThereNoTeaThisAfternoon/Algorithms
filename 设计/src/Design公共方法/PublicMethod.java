package Design公共方法;

public class PublicMethod {

    //String -> StringArray
    //["A..","AAA","..."]
    public static String[] stringToStringArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new String[0];
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '"')
                sB.append(input.charAt(i));
        }
        return sB.toString().split(",");
    }

    /**
     * String:[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]] -> int[][]
     * drawback: [[],[]] || [[],[3],[]]
     */
    public static int[][] stringTo2DIntegerArray(String input) {
        input = input.trim(); // 去除两旁多余空格
        input = input.substring(1, input.length() - 1); // 去除两旁括号
        if ("".equals(input)) {
            return new int[0][];
        }
        // 获取行数、列数
        int rows = 0, cols = 1;
        boolean SWITCH = true;
        for (char word : input.toCharArray()) {
            if (SWITCH && word == ',') {
                cols++;
            } else if (word == ']') {
                SWITCH = false;
                rows++;
            }
        }
        int[][] output = new int[rows][cols];
        int index = 0;
        for (int[] row : output) {
            for (int i = 0; i < row.length; i++) {
                StringBuilder sb = new StringBuilder();
                // 跳过非数字字符
                while (index < input.length() && !Character.isDigit(input.charAt(index))) {
                    index++;
                }
                // 为负数加上'-'
                if (input.charAt(index - 1) == '-') {
                    sb.append('-');
                }
                // 获取数字 10 111
                while (index < input.length() && Character.isDigit(input.charAt(index))) {
                    sb.append(input.charAt(index++));
                }
                row[i] = Integer.parseInt(sb.toString());
            }
        }
        return output;
    }
}
