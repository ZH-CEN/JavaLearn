package Experiment.Exp5_6;

public class CeilingFan extends ElecticalAppliance {
    private final char type = 'D';
    private double rate;

    CeilingFan(int number, String deviceName) {
        super(deviceName);
        this.number = number;
        this.R = 20; // 吊扇的电阻为10
        this.highestI = 12;
    }

    public void display() {
        System.out.println("@" + this.type + this.number + ":" + (int) this.rate + " " + (int) this.voltages[0] + "-" + (int) this.voltages[1] + " " + (I > highestI ?  "exceeding current limit error": ""));
    }

    void setRate() {
        double voltagediff = getVoltageDiff();

        if (voltagediff <= 150 && voltagediff >= 80) {
            this.rate = (getVoltageDiff() - 80) * (360 - 80)/(150-80) + 80;
        } else if (voltagediff < 80) {
            this.rate = 0;
        } else if (voltagediff > 150) {
            this.rate = 360;
        }
    }

    @Override
    public void run(double in, double out, double I) {
        setVoltage(0, in);
        setVoltage(1, out);
        this.I = I;
        setRate(); // 设置转速, 使本设备运行
    }

}
