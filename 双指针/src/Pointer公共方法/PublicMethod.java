package Pointer公共方法;

public class PublicMethod {
    //String -> IntegerArray
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new int[0];
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        int temp = 0;
        for (String part : parts) {
            output[temp++] = Integer.parseInt(part);
        }
        return output;
    }
}
