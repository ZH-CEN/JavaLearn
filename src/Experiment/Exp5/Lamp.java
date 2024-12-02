package Experiment.Exp5;

abstract public class Lamp extends ElecticalAppliance {
    protected double lightBrightness;

    Lamp(String deviceName) {
        super(deviceName);
    }

    abstract void setLightBrightness();

}
