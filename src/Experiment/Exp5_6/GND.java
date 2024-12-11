package Experiment.Exp5;

public class GND extends ElecticalAppliance{


    GND(String deviceName) {
        super(deviceName);
    }

    @Override
    public void display() {
        System.out.println("@" + this.deviceName + ":0");
    }

    @Override
    public void run(double voltage) {
        this.setVoltage(0, 0);
        this.setVoltage(1, voltage);
        for(ElecticalAppliance child : this.children){
            child.run(this.getVoltage(1));
        }
    }
}
