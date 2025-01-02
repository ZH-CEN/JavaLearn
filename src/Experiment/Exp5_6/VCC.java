package Experiment.Exp5_6;

public class VCC extends ElecticalAppliance{


    VCC(String deviceName) {
        super(deviceName);
    }

    @Override
    public void display() {
        return;
    }

    @Override
    public void run(double in ,double out, double I) {
        setVoltage(0, in);
        setVoltage(1, out);
        this.I = I;
    }
}
