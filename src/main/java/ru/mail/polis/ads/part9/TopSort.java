package ru.mail.polis.ads.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class TopSort {
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
        List<List<Integer>> arr = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int[] used = new int[n];
        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
            used[i] = 0;
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            arr.get(a - 1).add(b - 1);
        }
        boolean flag = true;
        for (int i = 0; i < n; ++i)
            if (used[i] == 0)
                flag = flag & dfs (i, used, arr, stack);
        if (!flag) {
            System.out.println("-1");
            return;
        }
        List<Integer> ans = new ArrayList<>();
        while (!stack.empty()) {
            ans.add(stack.pop());
        }
        for (Integer an : ans) {
            System.out.println(an + 1);
        }
    }

    private static boolean dfs(int v, int[] used, List<List<Integer>> arr, Stack<Integer> ans) {
        used[v] = 1;
        for (int i = 0; i < arr.get(v).size(); ++i) {
            int to = arr.get(v).get(i);
            if (used[to] == 1) return false;
            if (used[to] == 0)
                if(!dfs (to, used, arr, ans))
                    return false;

        }
        used[v] = 2;
        ans.push(v);
        return true;
    }
}
