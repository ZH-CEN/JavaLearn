package Experiment.Exp2.exp07;

public class WanShu {
    public static void main(String[] args) {
        for (int i = 1; i <= 1000; i++) {
            int total = 0;
            for (int j = 1; j < i/2+1; j++) {
                if(i%j==0)
                    total += j;
            }
            if(total==i)
                System.out.println(i);
        }
    }
}
