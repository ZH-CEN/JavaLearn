package Experiment.Exp5;

//白炽灯
public class IncandescentLamp extends Lamp {
    private String Type = "B";

    IncandescentLamp(int number, String deviceName) {
        super(deviceName);
        this.number = number;
    }

    @Override
    public void display() {
        System.out.println("@" + this.Type + this.number + ":" + (int) this.lightBrightness);
    }

    @Override
    void setLightBrightness() {
        if (this.getVoltageDiff() < 10)
            this.lightBrightness = 0;
        else if (this.getVoltageDiff() > 10 || this.getVoltageDiff() < 220) {
            this.lightBrightness = (this.getVoltageDiff() - 10) / (220 - 10) * (200 - 50) + 50;
        } else
            this.lightBrightness = 200;
    }

    @Override
    public void run(double voltage) {
        setVoltage(0, voltage);
        setVoltage(1, 0);
        setLightBrightness();
        display();
        for (ElecticalAppliance child : this.children) {
            child.run(getVoltage(0));
        }
    }

}
