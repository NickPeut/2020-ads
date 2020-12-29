package ru.mail.polis.ads.part5;

import java.util.Scanner;

public class NPP {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int[] dp = new int[n];
        int maxi = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (a[j] == 0)
                    continue;
                if (a[i] % a[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxi = Math.max(maxi, dp[i]);
        }
        System.out.println(maxi);
    }
}
