package Bit公共方法;

public class PublicMethod {
    //String -> IntegerArray
    public int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.equals(""))
            return new int[0];
        String[] strings = input.split(",");
        int[] output = new int[strings.length];
        int index = 0;
        for (String string : strings)
            output[index++] = Integer.parseInt(string);
        return output;
    }

}
