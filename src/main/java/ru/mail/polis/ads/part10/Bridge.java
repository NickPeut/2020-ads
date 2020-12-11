package ru.mail.polis.ads.part10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static java.util.Arrays.fill;

public class Bridge {
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

    static class Edge {
        int num, to;

        Edge(int to, int num) {
            this.num = num;
            this.to = to;
        }

    }

    static class Timer {
        public int get() {
            return tmr++;
        }

        @Override
        public String toString() {
            return "Timer{" +
                    "tmr=" + tmr +
                    '}';
        }

        private int tmr = 0;
    }

    static void dfs(int v, ArrayList<ArrayList<Edge>> ways, boolean[] used, int[] tin, int[] fup, Timer timer, boolean[] isBridge, int lastEdge) {
        used[v] = true;
        tin[v] = fup[v] = timer.get();
        for (Edge edge : ways.get(v)) {
            int to = edge.to;
            if (lastEdge == edge.num)
                continue;

            if (used[to]) {
                fup[v] = Math.min(fup[v], tin[to]);
            } else {
                dfs(to, ways, used, tin, fup, timer, isBridge, edge.num);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] > tin[v]) {
                    isBridge[edge.num] = true;
                }
            }
        }
    }


    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        ArrayList<ArrayList<Edge>> ways = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ways.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            ways.get(a).add(new Edge(b, i));
            ways.get(b).add(new Edge(a, i));
        }
        boolean[] used = new boolean[n];
        int[] tin = new int[n];
        int[] fup = new int[n];

        boolean[] isBridge = new boolean[m];

        Timer timer = new Timer();

        for (int i = 0; i < n; i++)
            if (!used[i])
                dfs(i, ways, used, tin, fup, timer, isBridge, -1);

        int counter = 0;
        for (int i = 0; i < m; i++) {
            if (isBridge[i]) {
                counter++;
            }
        }
        System.out.println(counter);
        for (int i = 0; i < m; i++) {
            if (isBridge[i]) {
                System.out.print(i + 1 + " ");
            }
        }

    }
}