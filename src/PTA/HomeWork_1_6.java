package PTA;

import java.util.Scanner;


public class HomeWork_1_6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                int n = sc.nextInt();
                if (n < 10)
                    System.out.println(n);
                else {
                    n = n - 10;
                    System.out.println((char)('A' + n));

                }
            }
        }
    }
}
