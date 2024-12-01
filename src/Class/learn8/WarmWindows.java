package Class.learn8;

public class WarmWindows extends Windows{
    private Windows innerWindows;
    private Windows outerWindows;
    public WarmWindows() {
        innerWindows = new Windows();
        outerWindows = new Windows();
    }

    void open() {
        innerWindows.open();
        outerWindows.open();
        System.out.println("哈哈嗨，两层窗户都开啦。");
    }

    void close() {
        innerWindows.close();
        outerWindows.close();
        System.out.println("哈哈嗨，两层窗户都关啦。");
    }

    void open(Windows windows) {
        windows.open();
    }

    void close(Windows windows) {
        windows.close();
    }
}
