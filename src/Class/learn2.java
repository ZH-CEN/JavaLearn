package Class;

import java.util.Random;

public class learn2 {
    public static void main(String[] args) {
//        test1.main(null);
//        test2.main(null);
        test3.main(null);
    }
}


enum Season {
    SPRING,SUMMER,AUTUMN,WINTER;

}

//public enum TrafficLight{
//    RED(){}
//
//}

class test3{
    public static void main(String[] args){
        double[] arr = {12.0,32,4321,54,43,432,432,432,143,21,5432};
        int x = 4;
        double[][] brr = new double[arr.length%x==0 ? arr.length:arr.length+1][];//这里的第一维的大小严谨需要用if判断，使用三目运算符也可以
        int j=0;
        for (int i = 0; i < arr.length/x; i++) {
            brr[i] = new double[x];
            for (int k = 0; k < x; k++) {
                brr[i][k] = arr[j];
                j++;
            }
        }
        if (arr.length%x != 0) {
            brr[arr.length / x] = new double[arr.length % x];
            for (int i = 0; i < arr.length%x; i++) {
                brr[arr.length / x][i] = arr[j];
                j++;
            }
        }

        for (int i = 0; i < brr.length; i++) {
            for (int k = 0; k <brr[i].length; k++) {
//                for (int l = 0; l < brr[i][k]; l++) {
                System.out.println(brr[i][k]);
//                }
            }
        }






    }
}

class test2{
    public static void main(String[] args){
        Random score = new Random();
        System.out.println(score.doubles());
    }
}


class test1{
    public static void main(String[] args) {

        double[] scores = new double[71];


        int[] list = new int[5];
        list[0] = 0;
        list[1] = 0;
        list[2] = 0;
        list[3] = 0;
        list[4] = 0;
        for (int i = 0; i < 71; i++) {
            scores[i] = Math.random()*100;
            System.out.println(scores[i]);
            if(scores[i]<=60){
                list[0]++;
            }
            else if(scores[i]>60 && scores[i]<=70){
                list[1]++;
            }
            else if(scores[i]>70 && scores[i]<=80){
                list[2]++;
            }
            else if(scores[i]>80 && scores[i]<=90){
                list[3]++;
            }
            else if(scores[i]>90 && scores[i]<=100){
                list[4]++;
            }
        }
//        for (int i = 0; i < list.length; i++) {
//            System.out.println(list[i]);
//        } arr.length/x
        System.out.println(list[-1]);
    }
}