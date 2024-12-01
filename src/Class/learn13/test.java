package Class.learn13;

public class test {
    public static void main(String[] args) {
//        int a = 1/0;
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        try{
            if(b == 0)
                throw new ArithmeticException("你滴，不能除0滴");
            else {
                System.out.println(a/b);
            }
        }
        catch(ArithmeticException e){
            System.out.println(e);
        }
    }

}
