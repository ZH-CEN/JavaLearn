package Experiment.Exp5;

public class VCC extends ElecticalAppliance{


    VCC(String deviceName) {
        super(deviceName);
    }

    @Override
    public void display() {
        return;
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
