package ru.mail.polis.ads.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class task3 {

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
        String s = in.next();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                count--;
                if (count < 0) {
                    break;
                }
            } else {
                count++;
            }
        }
        if (count == 0)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}