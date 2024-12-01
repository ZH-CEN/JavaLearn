package Experiment.Exp5;

//白炽灯
public class IncandescentLamp extends Lamp {
    private String Type = "B";

    IncandescentLamp(String type, String deviceName) {
        super(type, deviceName);
    }

    @Override
    void setLightBrightness() {
        if (this.getVoltageDiff() < 10)
            this.lightBrightness = 0;
        else if (this.getVoltageDiff() > 10 || this.getVoltageDiff() < 220) {
            this.lightBrightness = (this.getVoltageDiff() - 10) / (220 - 10) * (200 - 50) + 50;
        }
        else
            this.lightBrightness = 200;
    }

    @Override
    void print() {
        System.out.println(Type+number); // TODO: 更改打印内容
        System.out.println("LightBrightness: " + lightBrightness);
    }

    @Override
    public void run() {
        this.setLightBrightness();
    }

    @Override
    public CircuitNode operate(String command, CircuitNode circuitNode) {

        return null;
    }
}
