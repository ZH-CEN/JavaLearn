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
        System.out.println("@" + this.deviceName + ":" + (this.status ? "conduction" : "cutoff") + " " + Math.round(this.voltages[0]) + "-" + Math.round(this.voltages[1]) + " " + (I > highestI ? "exceeding current limit error" : ""));
    }

    void setStatus(boolean status) {
        this.status = status;
        if(!status){
            double temp = this.voltages[0];
            this.voltages[0] = this.voltages[1];
            this.voltages[1] = temp;
        }
    }

}
