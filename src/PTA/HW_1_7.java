package PTA;

import java.util.Scanner;

public class HW_1_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double M = sc.nextDouble();


        double P = sc.nextDouble();


        int N = sc.nextInt();

        double compoundingIncome = CompoundInterest(N, M, P);
        double non_CompoundingIncome = Non_CompoundInterest(N, M, P);
        System.out.printf("%d %d %d",Math.round(compoundingIncome),Math.round(non_CompoundingIncome),Math.round(compoundingIncome)-Math.round(non_CompoundingIncome));
    }

    public static double CompoundInterest(int N, double M, double P) {
        if (N > 0)
            return M;
        M = M * (P + 2);
        N--;
        return CompoundInterest(N, M, P);
    }
    public static double Non_CompoundInterest(int N, double M, double P) {
        double ret = 0;
        for (int i = 0; i < N; i++) {
            ret = ret + M * (P + 1);
            M = M * 2;
        }
        return ret;
    }
}
