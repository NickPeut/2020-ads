package ru.mail.polis.ads.part10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Condensation {

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


    static class Vertex {
        int used, colour;
        ArrayList<Integer> graph, graph_transposed;
    }

    ;

    static void dfs_g(int v, ArrayList<Vertex> components, ArrayList<Integer> list) {
        components.get(v).used = 1;
        for (int u : components.get(v).graph) {
            if (components.get(u).used != 1)
                dfs_g(u, components, list);
        }
        list.add(v);
    }

    static void dfs_tg(int v, int color, ArrayList<Vertex> components) {
        components.get(v).used = 2;
        components.get(v).colour = color;
        for (int u : components.get(v).graph_transposed) {
            if (components.get(u).used != 2)
                dfs_tg(u, color, components);
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int color = 0, answer = 0;

        ArrayList<Integer> list = new ArrayList<>(n);
        ArrayList<Vertex> components = new ArrayList<>();
        ArrayList<Set<Integer>> ribs = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            ribs.add(new HashSet<>());
        }
        for(int i = 0; i < n; i++) {
            components.add(new Vertex());
            components.get(i).graph = new ArrayList<>();
            components.get(i).graph_transposed = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1;
            components.get(a).graph.add(b);
            components.get(b).graph_transposed.add(a);
        }

        for (int i = 0; i < n; i++) {
            if (components.get(i).used != 1) dfs_g(i, components, list);
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            if (components.get(list.get(i)).used != 2) {
                dfs_tg(list.get(i), color, components);
                color++;
            }
        }
        for (Vertex vertex : components) {
            for (int j : vertex.graph) {
                if (vertex.colour != components.get(j).colour) {
                    ribs.get(vertex.colour).add(components.get(j).colour);
                }
            }
        }
        for (int i = 0; i < 10000; i++) answer += ribs.get(i).size();
        System.out.println(answer);
    }
}
