package ru.mail.polis.ads.part3;

import java.util.Scanner;

public class isHeap {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        if(isHead(n, arr, 1)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean isHead(int n, int[] arr, int i) {
        if (i * 2 > n) return true;
        if (arr[i - 1] > arr[i * 2 - 1]) return false;
        if (i * 2 + 1 > n) return isHead(n, arr, i * 2);
        if (arr[i - 1] > arr[i * 2]) return false;
        return isHead(n, arr, i * 2) && isHead(n, arr, i * 2 + 1);
    }
}
