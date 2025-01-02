package Experiment.Exp5_6;

public class Diode extends Controller {
    private char type = 'P';

    Diode(int number, String deviceName) {
        super(deviceName);
        this.number = number;
        this.highestI = 8;
    }

    Diode() {
    }

    @Override
    public void display() {
        System.out.println("@" + this.deviceName + ":" + (this.status ? "conduction" : "cutoff") + " " + (int) this.voltages[0] + "-" + (int) this.voltages[1] + " " + (I > highestI ? "exceeding current limit error" : ""));
    }

    void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void run(double in, double out, double I) {
        setVoltage(0, in);
        setVoltage(1, out);
        this.I = I;
    }

}
