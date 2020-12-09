package ru.mail.polis.ads.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FordBellman {
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
        int[][] arr = new int [m][3];
        for (int i = 0; i < m; i++) {
            arr[i][0] = in.nextInt() - 1;
            arr[i][1] = in.nextInt() - 1;
            arr[i][2] = in.nextInt();
        }
        int [] d = new int[n];
        for (int i = 1; i < n; i++) {
            d[i] = 300000;
        }

        d[0] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                if (d[arr[j][0]] + arr[j][2] < d[arr[j][1]])
                    d[arr[j][1]] = d[arr[j][0]] + arr[j][2];
            }
        }

        for (int i = 0; i < n; i++) {
            if(d[i] > 30000)
                System.out.print("30000 ");
            else
                System.out.print(d[i] + " ");
        }
    }

}
