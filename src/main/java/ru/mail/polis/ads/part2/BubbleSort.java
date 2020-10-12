package ru.mail.polis.ads.part2;

import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    int k = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = k;
                    ans += 1;
                }
            }
        }
        System.out.println(ans);
    }
}