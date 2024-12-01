package Class.HomeWork.HM2;

public class Fan {
    private int speed;  // 0表示停止，1表示低速，2表示中速，3表示高速

    public Fan() {
        this.speed = 0;  // 初始状态风扇为停止
    }

    // 获取当前风扇的速度
    public int getSpeed() {
        return speed;
    }

    // 设置风扇的速度
    public void setSpeed(int speed) {
        this.speed = speed;
        System.out.println("Fan speed set to: " + speed);
    }

    // 停止风扇
    public void stop() {
        this.speed = 0;
        System.out.println("Fan stopped.");
    }
}


