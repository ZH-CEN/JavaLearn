package Experiment.Exp5;

public abstract class Controller extends ElecticalAppliance{
    protected boolean status = false;

    Controller(String deviceName) {
        super(deviceName);
    }

    public void switchState(){
        if (this.status){
            this.status = false;
        }else{
            this.status = true;
        }
    }

    @Override
    public void run(double voltage) {
        for (ElecticalAppliance child : this.children) {
            child.run(voltage);
        }
    }
}
