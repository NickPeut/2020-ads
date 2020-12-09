package ru.mail.polis.ads.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.util.Arrays.fill;


public class ShortestWay2 {
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
        int start = in.nextInt() - 1;
        int finish = in.nextInt() - 1;
        ArrayList<ArrayList<Integer>> ways = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            ways.add(new ArrayList<>());
        }
        for(int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            ways.get(a).add(b);
            ways.get(b).add(a);
        }

        int[] dist = new int[n];
        int[] parent = new int[n];
        fill(dist, 1000000);
        dist[start] = 0;
        parent[start] = start;
        boolean[] used = new boolean[n];
        Queue<Integer> vertexes = new LinkedList<>();
        vertexes.add(start);
        used[start] = true;

        while (!vertexes.isEmpty()) {
            int current = vertexes.remove();
            for (Integer next : ways.get(current)) {
                if (!used[next]) {
                    used[next] = true;
                    parent[next] = current;
                    dist[next] = dist[current] + 1;
                    vertexes.add(next);
                }
            }
        }
        if(dist[finish] == 1000000)
            System.out.println("-1");
        else {
            System.out.println(dist[finish]);
            int current = finish;
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(finish + 1);
            while (parent[current] != start) {
                ans.add(parent[current] + 1);
                current = parent[current];
            }
            ans.add(start + 1);
            for (int i = ans.size() - 1; i >= 0; i--) {
                System.out.print(ans.get(i) + " ");
            }
        }
    }
}
