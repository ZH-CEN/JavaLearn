package Experiment.Exp5_6;

public abstract class Lamp extends ElecticalAppliance {
    protected double lightBrightness;

    Lamp(String deviceName) {
        super(deviceName);
    }

    public double getLightBrightness() {
        return this.lightBrightness;
    }

    protected abstract void setLightBrightness();
}