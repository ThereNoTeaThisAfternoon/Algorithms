package Stack公共方法;

public class PublicMethod {

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() < 1)
            return new int[0];
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        int index = 0;
        for (String part : parts)
            output[index++] = Integer.parseInt(part);
        return output;
    }

    /**
     * @param input ["2","1","+","3","*"]
     * @return output String[]{2,1,+,3,*}
     */
    public static String[] stringToStringArray(String input) {
        input = input.trim(); // 去除两旁多于空格
        input = input.substring(1, input.length() - 1); // 去除两旁中括号
        if (input.length() < 1) {
            return new String[0];
        }
        String[] output = input.split(",");
        for (int i = 0; i < output.length; ++i) {
            output[i] = output[i].substring(1, output[i].length() - 1);
        }
        return output;
    }
}
