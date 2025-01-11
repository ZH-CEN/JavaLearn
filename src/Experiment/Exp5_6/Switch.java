package Experiment.Exp5_6;

public class Switch extends Controller {
    private char type = 'K';
    Switch(int number, String deviceName) {
        super(deviceName);
        this.number = number;
        this.highestI = 20;
    }
    Switch(){

    }

    @Override
    public void display() {
        System.out.println("@" + this.deviceName + ":" + (this.status ? "closed" : "turned on") + " " + Math.round(this.voltages[0]) + "-" + Math.round(this.voltages[1]) + " " + ( I > highestI ?  "exceeding current limit error": ""));
    }
}
