package Experiment.Exp5;

public class CeilingFan extends ElecticalAppliance {
    private final char type = 'D';
    private double rate;

    CeilingFan(String type, String deviceName) {
        super(type, deviceName);
    }

    void setRate() {
        if(getVoltageDiff() <= 150 && getVoltageDiff() >= 80){
            this.rate = (getVoltageDiff()-80)*(360-80)+80;
        }
        else if(getVoltageDiff() < 80){
            this.rate = 80;
        }
        else if(getVoltageDiff() > 150){
            this.rate = 360;
        }
    }

    @Override
    public void run() {
        setRate();
    }

    @Override
    public CircuitNode operate(String command, CircuitNode circuitNode) {
        if(command.equals("run")){
            run();
//            return new ElectricWire(this.getVoltage(0));
        }
        else if(command.equals("link1")){
//            return new ElectricWire(this);
        }
        return null;
    }
}
