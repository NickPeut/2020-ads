package ru.mail.polis.ads.part2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MergeSort {
    static class Pair {
        long first;
        long second;

        public Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }
        public Pair(Pair data) {
            this.first = data.first;
            this.second = data.second;
        }
    }

    static void merge(Pair[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        Pair[] L = new Pair[n1];
        Pair[] R = new Pair[n2];
        for (int i = 0; i < n1; ++i) {
            L[i] = new Pair(arr[l + i]);
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = new Pair(arr[m + 1 + j]);
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].first <= R[j].first) {
                arr[k].first = L[i].first;
                arr[k].second = L[i].second;
                i++;
            }
            else {
                arr[k].first = R[j].first;
                arr[k].second = R[j].second;
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k].first = L[i].first;
            arr[k].second = L[i].second;
            i++;
            k++;
        }

        while (j < n2) {
            arr[k].first = R[j].first;
            arr[k].second = R[j].second;
            j++;
            k++;
        }
    }

    static void sort(Pair[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

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
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            long x, y;
            x = in.nextInt();
            y = in.nextInt();
            arr[i] = new Pair(x, y);
        }
        sort(arr, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i].first + " " + arr[i].second);
        }
    }
}