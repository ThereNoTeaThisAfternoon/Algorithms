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
}
