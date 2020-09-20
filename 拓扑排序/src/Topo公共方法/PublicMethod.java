package Topo公共方法;

public class PublicMethod {
    //[[1,0],[2,0],[3,1],[3,2]] -> Integer2DArray
    public int[][] stringToInteger2DArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new int[0][];

        int subLen = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ']')
                subLen++;
        }
        int[][] output = new int[subLen][2];
        int index = 0;
        for (int[] arr : output) {
            for (int i = 0; i < arr.length; i++) {
                while (input.charAt(index) < '0' || input.charAt(index) > '9')
                    index++;
                arr[i] = Integer.parseInt(String.valueOf(input.charAt(index++)));
            }
        }
        return output;
    }

    public String integerArrayToString(int[] input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer i : input)
            stringBuilder.append(i).append(", ");
        return "[" + stringBuilder.toString().substring(0, stringBuilder.length() - 2) + "]";
    }
}
