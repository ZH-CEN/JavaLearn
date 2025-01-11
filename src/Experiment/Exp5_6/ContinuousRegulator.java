package Experiment.Exp5_6;

public class ContinuousRegulator extends Controller {
    private final char type = 'L';

    ContinuousRegulator(int number, String deviceName) {
        super(deviceName);
        this.number = number;
        this.R = 0;
        this.highestI = 18;
    }

    void setRate(double rate) {
        if (rate <= 1 && rate >= 0) {
            this.rate = rate;
            this.status = true;
        } else {
            String[] args = {"非法输入，请输入0-1之间的数"};
            this.log(args);
        }
        if (rate > 0) {
            status = true;
        }
    }

    @Override
    public void display() {
        System.out.printf("@%c%d:%.2f", this.type, this.number, this.rate);
        System.out.println(" " + Math.round(this.voltages[0]) + "-" + Math.round(this.voltages[1]) + " " + (I > highestI ?  "exceeding current limit error": ""));
    }


}
