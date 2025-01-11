package Experiment.Exp5_6;

public abstract class Controller extends ElecticalAppliance{
    protected boolean status = false;
    protected double rate;

    Controller(String deviceName) {
        super(deviceName);
    }
    Controller(){

    }

    public void switchState(){
        this.status = !this.status;
    }

    @Override
    public double getResistance() {
        if (status) {
            return this.R;
        } else {
            return -1;
        }
    }

    double getRate(){
        return rate;
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
