package Experiment.Exp2.exp05;

public class ShuiXianHua {
    public static void main(String[] args) {
        for (int i = 100; i <= 999; i++) {
            if(Math.round(Math.pow(i%10, 3)+Math.pow((i/10)%10, 3)+Math.pow(i/100, 3)) == i)
                System.out.println(i);
        }
    }
}
