package Experiment.Exp5_6;

// 日光灯
public class FluorescentLamp extends Lamp {
    private String type = "R";

    FluorescentLamp(int number, String deviceName) {
        super(deviceName);
        this.number = number;
        this.R = 5; // 日光灯的电阻为5
        this.highestI = 5;
    }

    @Override
    public void display() {
        System.out.println("@" + this.type + this.number + ":" + (int) this.lightBrightness + " " + (int) this.voltages[0] + "-" + (int) this.voltages[1] + " " + (I > highestI ? "exceeding current limit error" : ""));
    }

    @Override
    protected void setLightBrightness() {
        if (this.getVoltageDiff() > 0)
            this.lightBrightness = 180;
        else
            this.lightBrightness = 0;
    }

    @Override
    public void run(double in, double out, double I) {
        setVoltage(0, in);
        setVoltage(1, out);
        this.I = I;
        setLightBrightness();
    }


}
