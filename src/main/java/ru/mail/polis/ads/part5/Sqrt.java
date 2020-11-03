package ru.mail.polis.ads.part5;
import java.util.Scanner;

public class Sqrt {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double c = in.nextDouble();
        double l = 0, r = c;
        double m;
        while(Math.abs(r - l) > 1e-9) {
            m = (r + l) / 2;
            if ((m * m + Math.sqrt(m)) - c < 0)
                l = m;
            else
                r = m;
        }
        System.out.println(r);
    }
}
