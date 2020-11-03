package ru.mail.polis.ads.part5;

import java.util.Scanner;
public class Diplomas {
    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        long w, h, n;
        w = in.nextLong();
        h = in.nextLong();
        n = in.nextLong();
        long l = Math.max(h, w), r = Math.max(h, w) * n;
        while(l < r) {
            long m = (r + l) / 2;
            long a1 = m / h;
            long a2 = m / w;
            if(n <= a1 * a2) {
                r = m;
            } else
                l = m + 1;
        }
        System.out.println(l);
    }
}
