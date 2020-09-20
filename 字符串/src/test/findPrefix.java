package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class findPrefix {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = line;
            int k = Integer.parseInt(in.readLine());
            int ret = new SolutionCopy2().maxVowels(s, k);
            String out = String.valueOf(ret);
            System.out.println(out);
        }
    }
}

class SolutionCopy2 {
    public int maxVowels(String s, int k) {
        int left = 0, right = 0, len = s.length();
        int max = 0;
        int count = 0;
        while (right < len) {
            if (isVowels(s.charAt(right)))
                count++;
            while (right - left + 1 == k) {
                max = Math.max(max, count);
                if (isVowels(s.charAt(left)))
                    count--;
                left++;
            }
            right++;
        }
        return max;
    }

    private boolean isVowels(char a) {
        return a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u';
    }
}

class Solution {

    public int isPrefixOfWord(String sentence, String searchWord) {
        sentence = sentence.trim();
        String[] parts = sentence.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].length() < searchWord.length())
                continue;
            if (parts[i].substring(0, searchWord.length()).equals(searchWord))
                return i + 1;
        }
        return -1;
    }
}