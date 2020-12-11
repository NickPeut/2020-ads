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

    static class Edge implements Comparable<Edge> {
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
        int[] par;
        int[] rank;

        DSF(int size) {
            par = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++)
                par[i] = i;
            for (int i = 0; i < size; i++)
                rank[i] = 1;
        }

        Boolean same(int a, int b) {
            return getParent(a) == getParent(b);
        }

        int getParent(int x) {
            if (x == par[x])
                return x;
            return par[x] = getParent(par[x]);
        }

        boolean union(int u, int v) {
            u = getParent(u);
            v = getParent(v);
            if (u == v)
                return false;
            if (rank[u] < rank[v]) {
                par[u] = v;
            } else {
                par[v] = u;
                if (rank[u] == rank[v])
                    rank[u]++;
            }
            return true;
        }
    }

    static int mstKruskal(Edge[] edges, int n) {
        DSF dsf = new DSF(n);
        Arrays.sort(edges);
        int ret = 0;
        for (Edge e : edges) {
            dsf.union(e.from, e.to);
            ret = e.cost;
            if (dsf.same(0, n - 1))
                return ret;
        }
        return -1;
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