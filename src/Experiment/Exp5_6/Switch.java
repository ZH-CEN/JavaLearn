package Experiment.Exp5;

public class Switch extends Controller {
    private char type = 'K';
    Switch(int number, String deviceName) {
        super(deviceName);
        this.number = number;
    }

    @Override
    public void display() {
        System.out.println("@" + this.type + ":" + (this.status ? "turned on" : "closed"));
    }

    @Override
    public void run(double voltage) {
        display();
        if(this.status){
            for (ElecticalAppliance child : this.children) {
                child.run(voltage);
            }
        }
    }

}
