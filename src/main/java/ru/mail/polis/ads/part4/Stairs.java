package ru.mail.polis.ads.part4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Stairs {
    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }


    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        int n = in.nextInt() + 1;
        int[] arr = new int[n];
        int[] dp = new int[n];

        arr[0] = 0;
        for (int i = 1; i < n; i++)
            arr[i] = in.nextInt();

        int k = in.nextInt();

        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            int sum = -1000000;
            for (int j = Math.max(0, i - k); j < i; j++)
                sum = Math.max(sum, dp[j]);
            dp[i] = sum + arr[i];
        }

        int ans = dp[n - 1];
        for (int j = Math.max(0, n - k); j < n; j++)
            ans = Math.max(ans, dp[j]);
        System.out.println(ans);
    }
}