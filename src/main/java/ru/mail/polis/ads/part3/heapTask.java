package ru.mail.polis.ads.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


class Heap {
    private final ArrayList<Integer> list;
    private int size = 0;

    Heap() {
        list = new ArrayList<>();
        list.add(0);
    }

    public void insert(int v) {
        size++;
        list.add(v);
        int i = size;
        while (i > 1 && list.get(i) > list.get(i / 2)) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    public int extract() {
        int max = list.get(1);
        swap(1, size);
        size--;
        int k = 1;
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && list.get(j) < list.get(j + 1)) j++;
            if (list.get(k) >= list.get(j)) break;
            swap(k, j);
            k = j;
        }
        list.remove(size + 1);
        return max;
    }

    private void swap(Integer i, Integer j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

}

public class heapTask {
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
        Heap heap = new Heap();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            if (a == 0) {
                int x = in.nextInt();
                heap.insert(x);
            } else {
                System.out.println(heap.extract());
            }
        }
    }
}
