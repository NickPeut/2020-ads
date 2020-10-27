package ru.mail.polis.ads.part4;

import java.util.Scanner;

public class CorrectBS {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int n = s.length();
        int [][] dp = new int [s.length()][s.length()];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = 0;
            }
        }

        int i0 = 0;
        while (i0 != n)
        for (int i = i0, j = 0; i < s.length(); i++, j++) {
            if(i == n - 1) i0++;
            if(i == j) {
                dp[j][i] = 1;
                continue;
            }

            if (isPair(s.charAt(j), s.charAt(i))) {
                dp[j][i] = dp[j + 1][i - 1];
            } else {
                dp[j][i] = Integer.MAX_VALUE;
                for(int k = j; k < i; k++) {
                    dp[j][i] = Math.min(dp[j][k] + dp[k + 1][i], dp[j][i]);
                }
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }


    }

    private static boolean isPair(char a, char b) {
        return (a == '(' && b == ')') || (a == '[' && b == ']');
    }
}
