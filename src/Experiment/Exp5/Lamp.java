package Experiment.Exp5;

abstract public class Lamp extends ElecticalAppliance {
    protected double lightBrightness;

    Lamp(String type, String deviceName) {
        super(type, deviceName);
    }

    abstract void setLightBrightness();

    abstract void print();
//    @Override
//    public void run(){
//        if(getVoltageDiff() > )
//    }

}
