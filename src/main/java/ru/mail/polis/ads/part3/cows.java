package ru.mail.polis.ads.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class cows {
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int left = 0;
        int right = arr[n - 1] - arr[0] + 1;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (check(mid, arr, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
    }

    private static boolean check(int x, int[] arr, int k) {
        int cows = 1;
        int last_cow = arr[0];
        for (int c : arr) {
            if (c - last_cow >= x) {
                cows++;
                last_cow = c;
            }
        }
        return cows >= k;
    }
}

