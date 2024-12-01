package Class.learn4;

public class Test {
    public static void main(String[] args) {
        ZhaoMing zm = new ZhaoMing(new Light(), new KaiGuan());

        zm.swittch("on");
//        System.out.println();
        zm.swittch("off");
    }
}
