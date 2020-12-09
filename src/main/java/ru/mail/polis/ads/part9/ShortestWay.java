package ru.mail.polis.ads.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.util.Arrays.fill;

public class ShortestWay {
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
        int INF = Integer.MAX_VALUE / 2; // "Бесконечность"
        int[][] graph = new int [n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                graph[i][j] = INF;
            }
            graph[i][i] = 0;
        }
        int s = in.nextInt() - 1;
        int f = in.nextInt() - 1;
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt();
            graph[a][b] = c;
            graph[b][a] = c;
        }

        boolean[] used = new boolean[n];
        int[] dist = new int[n];
        int[] p = new int[n];
        p[s] = s;
        fill(dist, INF);
        dist[s] = 0;
        for (; ; ) {
            int v = -1;
            for (int newV = 0; newV < n; newV++)
                if (!used[newV] && dist[newV] < INF && (v == -1 || dist[v] > dist[newV]))
                    v = newV;
            if (v == -1) break;
            used[v] = true;
            for (int j = 0; j < n; j++)
                if (!used[j] && graph[v][j] < INF) {
                    if (dist[j] > dist[v] + graph[v][j]) {
                        p[j] = v;
                        dist[j] = dist[v] + graph[v][j];
                    }
                }
        }

        System.out.println(dist[f]);
        List<Integer> ans = new ArrayList<>();
        for (int v = f; v != s; v = p[v])
            ans.add(v);
        ans.add(s);
        for(int i = ans.size() - 1; i >= 0; i--) {
            System.out.print(ans.get(i) + 1 + " ");
        }
    }
}
