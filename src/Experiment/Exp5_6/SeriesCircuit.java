package Experiment.Exp5_6;

import java.util.ArrayList;
import java.util.List;

public class SeriesCircuit extends ElecticalAppliance {
    private List<ElecticalAppliance> appliances;


    public SeriesCircuit() {
        super("Series Circuit");
        this.appliances = new ArrayList<>();
    }

    public SeriesCircuit(int num, String name) {
        super(name);
        number = num;
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

    public double getResistance() {
        double totalResistance = 0;
        if (iscut()) {
            return -1;
        }
        for (ElecticalAppliance appliance : appliances) {
            try {
                totalResistance += appliance.getResistance();
            } catch (Exception e) {
                appliance.display();
                System.out.println("Error: " + e.getMessage());
            }
        }
        return totalResistance;
    }

    public boolean iscut() {
        for (ElecticalAppliance appliance : appliances) {
            if (appliance instanceof Controller && !((Controller) appliance).status) {
                return true;
            }
        }
        for (ElecticalAppliance appliance : appliances) {
            if (appliance.getResistance() == -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run(double in, double out, double I) {
        setVoltage(0, in);
        setVoltage(1, out);
        this.I = I;
//        display();
//        System.out.println("@" + this.deviceName + ":" + (int) this.voltages[0] + "-" + (int) this.voltages[1]);

        // 环路检测。和处理
//        if (iscut() || (in == 0 && out == 0)) {
//            System.out.println("@" + this.deviceName + "断路!");
//            for (ElecticalAppliance appliance : appliances) {
//                appliance.setVoltage(0, 0);
//                appliance.setVoltage(1, 0);
//            }
//            return;
//        }

        // 串联电路的电压分配。
        double voltage = in - out;
        double totalResistance = getResistance();
        I = voltage / totalResistance;
        for (ElecticalAppliance appliance : appliances) {
            double applianceVoltage = I * appliance.getResistance();
            appliance.run(in, in - applianceVoltage, I);
            in = in - applianceVoltage;
        }
    }
}