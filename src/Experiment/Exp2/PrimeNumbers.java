package Experiment.Exp2;

import java.util.Scanner;
public class PrimeNumbers {
    static int[] prime;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        while(sc.hasNextInt()){
            if(is_prime(sc.nextInt()))
                System.out.println("是质数");
            else
                System.out.println("不是质数");
        }
    }

    static boolean is_prime(int n) {
        if(prime[n-100] == 1){
            return false;
        }
        else
            return true;
    }
//    static void oula(int top){
//        int[] prime = new int[(int)top+1];
//        int[] visit = new int[(int)top+1];
//
//        for (int i = 2; i < top; i++) {
//            if (visit[i] == 0){
//                if (prime[j] == 1){}
//                prime[++prime[0]] = i;
//            }
//            for (int j = 1; j < top/i; j++) {
//                visit[j*i] = 1;
//            }
//
//
//        }
//
//
//    }
}
