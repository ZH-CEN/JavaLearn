package Experiment.Exp5;

// 日光灯
public class FluorescentLamp extends Lamp {
    private String type = "R";

    FluorescentLamp(int number, String deviceName) {
        super(deviceName);
        this.number = number;
    }

    @Override
    public void display() {
        System.out.println("@" + this.type + this.number + ":" + (int) this.lightBrightness);
    }

    @Override
    void setLightBrightness() {
        if (this.getVoltageDiff() > 0)
            this.lightBrightness = 180;
        else
            this.lightBrightness = 0;
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
