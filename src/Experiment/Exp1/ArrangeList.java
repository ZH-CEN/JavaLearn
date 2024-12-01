package Experiment.Exp1;

public class ArrangeList {
    public static void main(String[] args) {
        char[] A = {'a','b','c'};
        char[] B = {'x','y','z'};
        int n = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(A[i] == 'a' && B[j] == 'x'){
                    continue;
                }
                if (A[i] == 'c' && (B[j] == 'x' || B[j] == 'z')) {
                    continue;
                }
                n++;
                System.out.printf("名单"+n+": ("+ A[i]+" vs "+B[j]+")\n");
            }
        }
    }
}
