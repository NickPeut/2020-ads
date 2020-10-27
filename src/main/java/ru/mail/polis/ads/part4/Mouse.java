package ru.mail.polis.ads.part4;

import java.util.Scanner;

public class Mouse {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), n = in.nextInt();
        int[][] corn = new int [m][n];
        int[][] pi = new int [m][n];
        int[][] pj = new int [m][n];
        int[][] dp = new int [m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                corn[i][j] = in.nextInt();
                pi[i][j] = 0;
                pj[i][j] = 0;
                dp[i][j] = -1;
            }
        }
        int i, j;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = corn[i][j];
                    continue;
                }
                if (i - 1 < 0) {
                    dp[i][j] = dp[i][j - 1] + corn[i][j];
                    pj[i][j] = -1;
                }
                else if (j - 1 < 0) {
                    dp[i][j] = dp[i - 1][j] + corn[i][j];
                    pi[i][j] = -1;
                } else {
                    if(dp[i - 1][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j] + corn[i][j];
                        pi[i][j] = -1;
                    } else {
                        dp[i][j] = dp[i][j - 1] + corn[i][j];
                        pj[i][j] = -1;
                    }
                }
            }
        }
        i = m - 1;
        j = n - 1;
        StringBuilder ans = new StringBuilder();
        while (pj[i][j] != 0 || pi[i][j] != 0) {
            if (pi[i][j] == 0) {
                ans.append('R');
            } else {
                ans.append('F');
            }
            int new_i = i + pi[i][j];
            int new_j = j + pj[i][j];
            i = new_i;
            j = new_j;
        }
        System.out.println(ans.reverse());

    }
}
