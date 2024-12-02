package Experiment.Exp5;

public class Kaiguan extends Controller {
    private char type = 'K';
    Kaiguan(String deviceName) {
        super(deviceName);
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
