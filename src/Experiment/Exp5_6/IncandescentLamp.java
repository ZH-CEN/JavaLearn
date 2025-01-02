package Experiment.Exp5_6;

public class IncandescentLamp extends Lamp {
    private String Type = "B";

    IncandescentLamp(int number, String deviceName) {
        super(deviceName);
        this.number = number;
        this.R = 10; // 白炽灯的电阻为10
        this.highestI =9;
    }

    @Override
    public void display() {
        System.out.println("@" + this.Type + this.number + ":" + (int) this.lightBrightness + " " + (int) this.voltages[0] + "-" + (int) this.voltages[1] + " " + (I > highestI ?  "exceeding current limit error": ""));
    }

    @Override
    protected void setLightBrightness() {
        if (this.getVoltageDiff() < 10)
            this.lightBrightness = 0;
        else if (this.getVoltageDiff() > 10 || this.getVoltageDiff() < 220) {
            this.lightBrightness = (this.getVoltageDiff() - 10) / (220 - 10) * (200 - 50) + 50;
        } else
            this.lightBrightness = 200;
    }

    @Override
    public void run(double in, double out, double I) {
        setVoltage(0, in);
        setVoltage(1, out);
        this.I = I;
        setLightBrightness();
    }
}