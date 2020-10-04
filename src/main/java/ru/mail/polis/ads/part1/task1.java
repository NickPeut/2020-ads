package ru.mail.polis.ads.part1;

import java.util.Scanner;

public class task1 {
    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(num / 10 + " " + num % 10);
        in.close();
    }
}
