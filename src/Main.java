public class Main {
    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
        String zh = "你好hello";
        String en = "hello";
        System.out.println(zh.getBytes());
    }
}


class Test {
    public static void main(String[] args) {
        return;
    }
}




//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        double a, b, c;
//        while (sc.hasNext()) {
//            a = sc.nextDouble();
//            b = sc.nextDouble();
//            c = sc.nextDouble();
//            if (is_strangle(a,b,c))
//                System.out.printf("%.2f\n",Math.round(Area(a,b,c)*100)/100.0);
//            else
//                System.out.println("Input Error!\n");
//        }
//    }
//
//    public static double Area(double a, double b, double c) {
//        double p = (a + b + c)/2;
//        return Math.sqrt(p * (p - a) * (p - c) * (p - b));
//    }
//    public static boolean is_strangle(double a, double b, double c) {
//        if(a>b && a>c && a<b+c)
//            return true;
//        else if(b>c && b>a && b<a+c)
//            return true;
//        else if(c>b && c>a && c<a+b)
//            return true;
//        return false;
//    }
//}