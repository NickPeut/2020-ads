package ru.mail.polis.ads.part5;

import java.util.Scanner;

public class TemplateAndWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1, str2;
        str1 = in.next();
        str2 = in.next();
        if(str1.equals(str2)) {
            System.out.println("YES");
            return;
        }
        if(str1.contains("*") || str1.contains("?")) {
            String k = str1;
            str1 = str2;
            str2 = k;
        }
        boolean[][] dp = new boolean[str1.length() + 1][str2.length() + 1];
        dp[0][0] = true;

        for (int i = 1; i < str1.length() + 1; i++) {
            for (int j = 1; j < str2.length() + 1; j++) {
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else
                    switch (str2.charAt(j - 1)) {
                        case '?':
                            dp[i][j] = dp[i - 1][j - 1];
                            continue;
                        case '*':
                            dp[i][j] = dp[i - 1][j - 1] | dp[i][j - 1] | dp[i - 1][j];
                            continue;
                        default:
                            dp[i][j] = false;
                    }
            }
        }
        if (dp[str1.length()][str2.length()])
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
