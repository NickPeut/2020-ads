package ru.mail.polis.ads.part2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class TrickySort {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        Arrays.sort(arr, (a, b) -> {
            if(a % 10 == b % 10)
                return a - b;
            return (a % 10) - (b % 10);
        });

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}