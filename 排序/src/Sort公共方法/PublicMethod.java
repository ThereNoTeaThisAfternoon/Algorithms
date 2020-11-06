package Sort公共方法;


public class PublicMethod {

    /**
     * String:[0,1,2,3,4,5,6,7,8] -> int[]
     */
    public static int[] stringToIntegerArray(String input) {
        input = input.trim(); // 去除两旁多余空格
        input = input.substring(1, input.length() - 1); // 去除两旁括号
        if (input.equals("")) {
            return new int[0];
        }
        String[] parts = input.split(","); // regular expression
        int[] output = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            output[i] = Integer.parseInt(parts[i]);
        }
        return output;
    }

}
