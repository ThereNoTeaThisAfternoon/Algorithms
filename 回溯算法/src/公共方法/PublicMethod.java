package 公共方法;

public class PublicMethod {

    /**
     * 输入一个字符串转换为一个整型的数组
     *
     * @param input [2,3,5]
     * @return int[]{2,3,5};
     */
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        int index = 0;
        for (var part : parts) {
            output[index++] = Integer.parseInt(part);
        }
        return output;
    }

    /**
     * 输入一个字符串，返回一个矩形二维字符数组
     *
     * @param input [[A,B,C,E],[S,F,C,S],[A,D,E,E]]
     * @return new char[][]{{A,B,C,E},{S,F,C,S},{A,D,E,E}};
     */
    public static char[][] stringTo2DCharacterArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new char[0][];
        }
        int rowsCount = 0;
        int colsCount = 0;
        char[] parts = input.toCharArray();
        for (char part : parts) {
            if (part == ']') {
                rowsCount++;
            } else if ('A' <= part && part <= 'Z') {
                colsCount++;
            }
        }
        char[][] output = new char[rowsCount][colsCount / rowsCount];
        int index = 0;
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                while (parts[index] < 'A' || 'Z' < parts[index]) {
                    index++;
                }
                output[i][j] = parts[index++];
            }
        }
        return output;
    }
}
