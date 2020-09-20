package String公共方法;

import java.util.ArrayList;
import java.util.List;

public class PublicMethod {
    //String -> List<List<String>>
    //例子[["London","New York","哈 哈"],["New York"],["Lima","Sao Paulo"]]
    public List<List<String>> stringToString2dList(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new ArrayList<>();
        int subListLen = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(0, 0);
        for (char c : input.toCharArray()) {
            if (c == '\"')
                arrayList.set(subListLen, arrayList.get(subListLen) + 1);
            if (c == ']') {
                arrayList.set(subListLen, arrayList.get(subListLen) >> 1);
                subListLen++;
                arrayList.add(subListLen, 0);
            }
        }
        String[] strings = input.replaceAll("[^a-zA-Z0-9, ]", "").split(",");
        int index = 0;
        List<List<String>> output = new ArrayList<>();
        for (int i = 0; i < subListLen; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < arrayList.get(i); j++) {
                list.add(strings[index++]);
            }
            output.add(list);
        }
        return output;
    }

    public String integerListToString(List<Integer> input) {
        if (input.size() == 0)
            return "[]";
        StringBuilder sB = new StringBuilder();
        for (Integer i : input) {
            sB.append(i).append(", ");
        }
        return "[" + sB.toString().substring(0, sB.length() - 1) + "]";
    }

    //Strings -> StringArray
    public static String[] stringToStringArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new String[0];

        String[] output = input.split(",");
        for (int i = 0; i < output.length; i++) {
            output[i] = output[i].trim();
            output[i] = output[i].substring(1, output[i].length() - 1);
            output[i] = output[i].trim();
        }
        return output;
    }
}
