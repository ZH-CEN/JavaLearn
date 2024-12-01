package Class.learn8;

public class Windows {
    private boolean status = false;

    public Windows() {}
    void open(){
        status = true;
        System.out.println("哈哈哈，窗户开啦");
    }

    void close(){
        status = false;
        System.out.println("哈哈嗨，窗户关了");
    }

}
