package Experiment.Exp5_6;

public class SteppedRegulator extends Controller {
    private final char type = 'F';
    private int step;

    SteppedRegulator(int number, String deviceName) {
        super(deviceName);
        this.number = number;
        this.highestI = 18;
    }

    void setStep(int step) {
        this.step = step;
        if (step != 0)
            status = true;
    }

    void setStep(char ch) {
        if (ch == '+')
            this.step++;
        else if (ch == '-')
            this.step--;
        else {
            String[] args = {"非法输入，请输入+或-"};
            this.log(args);
        }
        if (step > 0)
            status = true;
    }

    void setOutVoltage(int out_id, double voltage) {
        this.voltages[out_id] = voltage * getRate();
    }

    double getRate() {
        switch (this.step) {
            case 0:
                rate = 0;
                break;
            case 1:
                rate = 0.3;
                break;
            case 2:
                rate = 0.6;
                break;
            case 3:
                rate = 0.9;
                break;
            default:
                rate = 0;
        }
        return rate;
    }

    @Override
    public void display() {
        System.out.println("@" + this.type + this.number + ":" + this.step + " " + (int) this.voltages[0] + "-" + (int) this.voltages[1] + " " + (I > highestI ? "exceeding current limit error" : ""));
    }

    @Override
    public void run(double in, double out, double I) {
        setVoltage(0, in);
        setVoltage(1, out);
        this.I = I;
        return;
    }
}
