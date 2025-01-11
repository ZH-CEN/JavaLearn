package Experiment.Exp5_6;

public class FloorFan extends ElecticalAppliance {
    private final char type = 'A';
    private double rate;

    FloorFan(int number, String deviceName) {
        super(deviceName);
        this.number = number;
        this.R = 20; // 落地扇的电阻为20
        this.highestI = 14;
    }

    public void display() {
        System.out.println("@" + this.type + this.number + ":" + Math.round(this.rate) + " " + Math.round(this.voltages[0]) + "-" + Math.round(this.voltages[1]) + " " + (I > highestI ? "exceeding current limit error" : ""));
    }

    void setRate() {
        double voltagediff = getVoltageDiff();

        if (voltagediff >= 80 && voltagediff < 100) {
            this.rate = 80;
        } else if (voltagediff >= 100 && voltagediff < 120) {
            this.rate = 160;
        } else if (voltagediff >= 120 && voltagediff < 140) {
            this.rate = 260;
        } else if (voltagediff >= 140) {
            this.rate = 360;
        } else {
            this.rate = 0;
        }
    }

    @Override
    public void run(double in, double out, double I) {
        setVoltage(0, in);
        setVoltage(1, out);
        this.I = I;
        setRate(); // 设置转速, 使本设备运行
    }
    @Override
    public void run() {
        setRate(); // 设置转速, 使本设备运行
    }
}