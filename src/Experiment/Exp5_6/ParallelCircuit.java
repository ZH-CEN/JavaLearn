package Experiment.Exp5_6;

import java.util.ArrayList;
import java.util.List;

public class ParallelCircuit extends ElecticalAppliance {
    private List<ElecticalAppliance> appliances;

    public ParallelCircuit() {
        super("Parallel Circuit");
        this.appliances = new ArrayList<>();
    }

    public ParallelCircuit(int num, String name) {
        super(name);
        this.number = num;
        this.appliances = new ArrayList<>();
    }

    public void addAppliance(ElecticalAppliance appliance) {
        this.appliances.add(appliance);
    }

    @Override
    public void display() {
        System.out.println("@" + this.deviceName + ":" + (int) this.voltages[0] + "-" + (int) this.voltages[1]);
        for (ElecticalAppliance appliance : appliances) {
            appliance.display();
        }
    }

    public boolean iscut() {
        if(this.getResistance() == -1)
            return true;
        return false;
    }

    @Override
    public double getResistance() {
        double totalResistance = 0;
        boolean flag = false;
        for (ElecticalAppliance appliance : appliances) {
            if(appliance.iscut()){
                flag = true;
                continue;
            }
//            System.out.println(appliance.deviceName + " " + appliance.getResistance());
            totalResistance += 1 / appliance.getResistance();
        }
        if(totalResistance == 0 && flag){

            return -1;
        }
        return 1 / totalResistance;
    }

    public void run(double in, double out, double I) {
        setVoltage(0, in);
        setVoltage(1, out);
        this.I = I;
//        System.out.println("@" + this.deviceName + ":" + (int) this.voltages[0] + "-" + (int) this.voltages[1]);

        // Check for open circuit
        if (iscut() || (in == 0 && out == 0)) {
            System.out.println("@" + this.deviceName + "断路!");
//            for (ElecticalAppliance appliance : appliances) {
//                appliance.setVoltage(0, 0);
//                appliance.setVoltage(1, 0);
//            }
            return;
        }
        // Run each appliance with the same input and output voltage
        for (ElecticalAppliance appliance : appliances) {
            if (!appliance.iscut()) {
                appliance.run(in, out, -1);
            }
            else
                appliance.setVoltage(0,in);
        }
    }
}