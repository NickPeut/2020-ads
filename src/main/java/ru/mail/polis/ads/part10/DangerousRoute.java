package ru.mail.polis.ads.part10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class DangerousRoute {
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

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        Edge(int u, int v, int w) {
            this.from = u;
            this.to = v;
            this.cost = w;
        }

        @Override
        public int compareTo(Edge edge) {
            if (cost != edge.cost) return cost < edge.cost ? -1 : 1;
            return 0;
        }
    }


    static class DSF {
        int[] set;
        int[] rnk;

        DSF(int size) {
            set = new int[size];
            rnk = new int[size];
            for (int i = 0; i < size; i++)
                set[i] = i;
        }

        int set(int x) {
            return x == set[x] ? x : (set[x] = set(set[x]));
        }

        boolean union(int u, int v) {
            if ((u = set(u)) == (v = set(v)))
                return false;
            if (rnk[u] < rnk[v]) {
                set[u] = v;
            } else {
                set[v] = u;
                if (rnk[u] == rnk[v])
                    rnk[u]++;
            }
            return true;
        }
    }
    static int mstKruskal(Edge[] edges, int n) {
        DSF dsf = new DSF(n);
        Arrays.sort(edges);
        int ret = 0;
        for (Edge e : edges)
            if (dsf.union(e.from, e.to))
                ret = e.cost;
        return ret;
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int cost = in.nextInt();
            edges[i] = new Edge(from, to, cost);
        }

        System.out.print(mstKruskal(edges, n));
    }

}