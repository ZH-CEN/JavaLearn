package Experiment.Exp5;

public class SteppedRegulator extends Controller {
    private final char type = 'F';
    private int step;

    SteppedRegulator(int number, String deviceName) {
        super(deviceName);
        this.number = number;
    }

    void setStep(int step) {
        this.step = step;
        if (step != 0)
            this.status = true;
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
    }

    void setOutVoltage(int out_id, double voltage) {
        double rate;
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
        this.voltage[out_id] = voltage * rate;
    }

    @Override
    public void display() {
        System.out.println("@" + this.type + this.number + ":" + this.step);
    }

    @Override
    public void run(double voltage) {
        setOutVoltage(1, voltage);
        display();

        if (this.step != 0) {
            for (ElecticalAppliance child : this.children) {
                child.run(getVoltage(1));
            }
        }
    }
}
