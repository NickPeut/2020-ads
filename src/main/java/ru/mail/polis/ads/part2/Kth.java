/*package ru.mail.polis.ads.part2;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Kth {
    public static void kth(ArrayList<BigInteger> a, int low, int high, int n) {
        while (true) {
            int k = randomizedPartition(a, low, high);
            if (n < k)
                high = k;
            else if (n > k)
                low = k + 1;
            else
                return;
        }
    }

    static Random rnd = new Random();

    static int randomizedPartition(ArrayList<BigInteger> a, int low, int high) {
        swap(a, low + rnd.nextInt(high - low), high - 1);
        BigInteger separator = a.get(high - 1);
        int i = low - 1;
        for (int j = low; j < high; j++)
            if (a.get(j).compareTo(separator) >= 0)
                swap(a, ++i, j);
        return i;
    }

    static void swap(ArrayList<BigInteger> a, int i, int j) {
        BigInteger t = a.get(i);
        a.set(i, a.get(j));
        a.set(j, t);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            int k = Integer.parseInt(in.nextLine());
            String[] heights = in.nextLine().split(" ");
            ArrayList<BigInteger> arr = new ArrayList<>(heights.length);

            for (String height : heights) {
                arr.add(new BigInteger(height));
            }

            kth(arr, 0, arr.size(), k);
            System.out.println(arr.get(k - 1));
        }
    }
}
*/