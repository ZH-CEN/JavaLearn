package Experiment.Exp2.exp06;

import java.util.Random;

public class GetMaxAndLessmax {
    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println("数组如下：");
        int[] arr = new int[100];
        arr[0] = rand.nextInt(100);
        System.out.printf(arr[0] + " ");
        int max, lmax;
        max=arr[0];
        lmax=arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = rand.nextInt(100);
            System.out.printf(arr[i] + " ");
            if(arr[i]>max){
                lmax = max;
                max = arr[i];
            }
        }
        System.out.println("\nmax:\t"+ max +"\nlessmax:\t"+ lmax);
    }
}
