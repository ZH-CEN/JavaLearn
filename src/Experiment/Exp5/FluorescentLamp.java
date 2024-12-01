package Experiment.Exp5;

// 日光灯
public class FluorescentLamp extends Lamp {
    private String Type = "R";

    FluorescentLamp(String type, String deviceName) {
        super(type, deviceName);
    }

    @Override
    void setLightBrightness() {
        if (this.getVoltageDiff() > 0)
            this.lightBrightness = 180;
        else
            this.lightBrightness = 0;
    }

    @Override
    void print() {

    }


    @Override
    public void run() {
        setLightBrightness();
    }

    @Override
    public CircuitNode operate(String command, CircuitNode circuitNode) {
        return null;
    }
}
