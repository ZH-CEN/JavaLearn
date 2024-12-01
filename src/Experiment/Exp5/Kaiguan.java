package Experiment.Exp5;

public class Kaiguan extends ElecticalAppliance {

    Kaiguan(String type, String deviceName) {
        super(type, deviceName);
    }

    @Override
    public void run() {
        for (ElecticalAppliance child :this.children){
            child.run();
        }
    }

    @Override
    public CircuitNode operate(String command, CircuitNode circuitNode) {
        if(command.equals("link1")){
            circuitNode.children.add(this);
        }
        else if(command.equals("run")){
            run();
        }
        return null;
    }
}
