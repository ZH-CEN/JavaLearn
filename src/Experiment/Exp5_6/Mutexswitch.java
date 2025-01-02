package Experiment.Exp5_6;

public class Mutexswitch extends Controller {
    private char type = 'H';
    public Switch mutex1, mutex2;


    Mutexswitch(int number, String deviceName) {
        super(deviceName);
        this.number = number;
        this.mutex1 = new Switch();
        this.mutex2 = new Switch();
        mutex1.R = 5;
        mutex2.R = 10;
        this.highestI = 20;
        this.mutex1.switchState();
    }

    public void switchState() {
        this.mutex1.switchState();
        this.mutex2.switchState();
    }

    Controller init(int num) {
        if (num == 2) {
            return this.mutex1;
        } else if (num == 3) {
            return this.mutex2;
        } else if (num == 1) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public void display() {
        System.out.println("@" + this.deviceName + ":" + (this.mutex1.status ? "closed" : "turned on") + " " + (this.mutex2.status ? "closed" : "turned on") + " " + (int) this.mutex1.voltages[0] + "-" + (int) this.mutex1.voltages[1] + "-" + (int) this.mutex2.voltages[1] + " " + (I > highestI ?  "exceeding current limit error": ""));
        // TODO : 还没改的.
    }

    @Override
    public void run(double in, double out, double I) {
        setVoltage(0, in);
        setVoltage(1, out);
        this.I = I;
        // 纯粹是为了继承Controller, 没有实际意义, 封装两个空开就是唯一用处. 实质上根本不会调用这个方法.
    }

}