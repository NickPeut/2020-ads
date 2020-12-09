package ru.mail.polis.ads.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Cycles {
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

    static void dfs(int v, int p, ArrayList<ArrayList<Integer>> ways, boolean[] used, int[] tin, int[] fup, int timer, boolean[] haveNotBridge) {
        used[v] = true;
        tin[v] = fup[v] = timer++;
        for (int i = 0; i < ways.get(v).size(); ++i) {
            int to = ways.get(v).get(i);
            if (to == p) continue;
            if (used[to])
                fup[v] = Math.min(fup[v], tin[to]);
            else {
                dfs(to, v, ways, used, tin, fup, timer, haveNotBridge);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to] <= tin[v]) {
                    haveNotBridge[v] = false;
                    haveNotBridge[to] = false;
                }
            }
        }
    }


    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
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
        boolean[] used = new boolean[n];
        int[] tin = new int[n];
        int[] fup = new int[n];
        boolean[] haveNotBridge = new boolean[n];
        int timer = 0;
        for(int i = 0; i < n; i++)
            haveNotBridge[i] = true;
        for (int i = 0; i < n; ++i)
            if (!used[i])
                dfs(i, -1, ways, used, tin, fup, timer, haveNotBridge);

        for(int i = 0; i < n; i++) {
            if(!haveNotBridge[i]) {
                System.out.println("Yes");
                System.out.println(i + 1);
                return;
            }
        }

        System.out.println("No");

    }


}
