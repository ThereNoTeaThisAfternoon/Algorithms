package Design公共方法;

import java.util.ArrayList;
import java.util.List;

public class PublicMethod {
    //String -> StringArray
    public String[] stringToStringArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0)
            return new String[0];
        String[] parts = input.split("\"");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].equals("") && !parts[i].equals(","))
                list.add(parts[i]);
        }
        String[] output = new String[list.size()];
        int index = 0;
        for (String l : list)
            output[index++] = l;
        return output;
    }
}
