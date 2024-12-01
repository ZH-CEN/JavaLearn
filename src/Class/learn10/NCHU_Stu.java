package Class.learn10;

public class NCHU_Stu extends Student implements Fly {

    public NCHU_Stu(String[] parms) {
        super(parms);
    }

    @Override
    public void fly() {
        System.out.println("fei");
    }
}