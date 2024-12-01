package Class.learn13;

public class test02 {
    public static void main(String[] args) {
        int a = 10;
        int[] b;
        b = new int[]{1, 2, 3, 4, 5, 6, 7, 7, 8, 0};

        try{
            for (int i = 0; i < b.length+1; i++) {
                System.out.println(a/b[i]);
            }


        }
        catch (Exception e){}
    }
}
