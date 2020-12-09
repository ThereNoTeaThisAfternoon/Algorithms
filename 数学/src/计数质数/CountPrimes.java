package 计数质数;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FileName: CountPrimes.java
 * 类的详细说明
 * 统计所有小于非负整数 n 的质数的数量。
 * 0 <= n <= 5 * 106
 *
 * @author &#x8c2f;&#x535a;
 * @version 1.00
 * @date 2020.12.3 - 下午 10:54
 * @label Math
 */
public class CountPrimes {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("请输入一个非负整数n：10");
        while ((line = in.readLine()) != null) {
            int result = new Solution().countPrimes(Integer.parseInt(line));
            System.out.println(result);
        }
    }
}

class Solution {
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            int j = 2;
            while (j < Math.sqrt(i) && i % j == 1) {
                j++;
            }
            if (j > Math.sqrt(i)) {
                count++;
            }
        }
        return count;
    }
}

class SolutionCopy {
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    public boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}

class SolutionCopy2 {
    public int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }
}

class SolutionCopy3 {
    public int countPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }
}
