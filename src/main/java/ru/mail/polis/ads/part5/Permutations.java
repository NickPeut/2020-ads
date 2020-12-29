package ru.mail.polis.ads.part5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Permutations {

    static void finder(int len, int maxLen, boolean[] used, List<Integer> permutation) {
        if (len == maxLen) {
            for (int elem : permutation)
                System.out.print(elem + " ");
            System.out.println("");
            return;
        }
        for (int i = 1; i <= maxLen; i++) {
            if (!used[i]) {
                used[i] = true;
                permutation.add(i);
                finder(len + 1, maxLen, used, permutation);
                permutation.remove(permutation.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean[] used = new boolean[n + 1];
        List<Integer> current = new ArrayList<>(n);
        finder(0, n, used, current);
    }
}
