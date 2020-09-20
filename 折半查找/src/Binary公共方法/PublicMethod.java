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

    //string -> IntegerArray  [[1,5,9],[10,11,13],[12,13,15]]
    public static int[][] stringTo2DIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new int[0][];
        int n = 0;
        for (char ch : input.toCharArray())
            if (ch == ']')
                n++;
        int[][] out = new int[n][n];
        int i = 0;
        StringBuilder str = new StringBuilder();
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < n; b++) {
                while (i < input.length() && !Character.isDigit(input.charAt(i)))
                    i++;
                while (i < input.length() && Character.isDigit(input.charAt(i))) {
                    str.append(input.charAt(i++));
                }
                out[a][b] = Integer.parseInt(str.toString().toString());
                str = new StringBuilder();
            }
        }
        return out;
    }
}
