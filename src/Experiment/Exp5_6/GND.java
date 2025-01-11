package Experiment.Exp5_6;

public class GND extends ElecticalAppliance {


    GND(String deviceName) {
        super(deviceName);
    }

    @Override
    public void display() {
        System.out.println("@" + this.deviceName + ":0" + " " + (int) this.voltages[0] + "-" + (int) this.voltages[1]);
    }

    @Override
    public void run(double in, double out, double I) {
        setVoltage(0, in);
        setVoltage(1, out);
        this.I = I;
    }

    @Override
    public void run() {

    }
}
