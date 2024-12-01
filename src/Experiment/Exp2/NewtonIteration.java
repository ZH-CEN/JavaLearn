package Experiment.Exp2;
import java.util.Scanner;

public class NewtonIteration{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a = 0,n = 0,c = 0;
        for (int i = 0; i < 2; i++) {
            if (sc.hasNextDouble())
                a = sc.nextDouble();
            if (sc.hasNextDouble())
                n = sc.nextDouble();
            if (sc.hasNextDouble())
                c = sc.nextDouble();
            StandardFunction f = new StandardFunction(a, n, c);
            System.out.println("该函数平方根(精确到0.001)为： " + (double)Math.round(NEWTONMETHOD(f)*10001)/1000);

        }
    }
    static double NEWTONMETHOD(StandardFunction f1) {
        double ret = 1;
//        double x = ;
        StandardFunction f1_ = f1.qiudao();
//        StandardFunction f2_ = f2.qiudao();
        double temp = 2;
        while(Math.abs(temp - ret) > 0.001) {
            temp = ret;
            ret = ret - (f1.count(ret)/f1_.count(ret));
        }
        return ret;
    }
}


class StandardFunction {
    double a,n,c;

    StandardFunction(double a1, double n1, double c1){
        this.a = a1;
        this.n = n1;
        this.c = c1;
    }

    double count(double x){
        double ret = Math.pow(x, this.n);
        return ret * this.a + this.c;
    }

    StandardFunction qiudao(){
        double a = this.a *  this.n;
        double n = this.n - 1;
        double c = 0;
        return new StandardFunction(a,n,c);
    }
}


