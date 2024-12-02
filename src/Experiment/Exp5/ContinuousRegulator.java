package Experiment.Exp5;

public class ContinuousRegulator extends Controller {
    private final char type = 'L';
    private double rate;

    ContinuousRegulator(int number, String deviceName) {
        super(deviceName);
        this.number = number;
    }

    void setRate(double rate) {
        if (rate <= 1 && rate >= 0) {
            this.rate = rate;
            this.status = true;
        } else {
            String[] args = {"非法输入，请输入0-1之间的数"};
            this.log(args);
        }
    }

    @Override
    public void display() {
        System.out.printf("@%c%d:%.2f%n", this.type, this.number, this.rate);
    }

    @Override
    public void run(double voltage) {
        setVoltage(0, voltage);
//        System.out.println(this.rate);
        setVoltage(1, getVoltage(0) * this.rate);
        display();
//        System.out.println(getVoltage(1));
        if (this.rate != 0) {
//            System.out.println("孩子结点：" + this.children.size());
            for (ElecticalAppliance child : this.children) {
                child.run(getVoltage(1));
            }
        }
    }
}
