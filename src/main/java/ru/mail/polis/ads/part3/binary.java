package ru.mail.polis.ads.part3;

import java.io.*;
import java.util.StringTokenizer;

public class binary {
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
        int m = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int k = in.nextInt();
            if (checkKInArr(n, arr, k)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean checkKInArr(int n, int[] arr, int k) {
        int l = 0, r = n;
        while(r - l > 1) {
            int m = (r + l) / 2;
            if(arr[m] <= k) {
                l = m;
                if(arr[m] == k) {
                    return true;
                }
            } else {
                r = m;
            }
        }
        return arr[l] == k;
    }
}
