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
        System.out.println("@" + this.deviceName + ":" + Math.round(this.voltages[0]) + "-" + Math.round(this.voltages[1]));
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
                this.R = -1;
                return true;
            }
        }
        for (ElecticalAppliance appliance : appliances) {
            if (appliance.getResistance() == -1) {
                this.R = -1;
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
        run();
    }

    @Override
    public void run() {
        double in = getVoltage(0);
        double out = getVoltage(1);
        double I = this.I;

        if (iscut()) {  // 检测到断路
            int leftIndex = 0;
            int rightIndex = appliances.size() - 1;

            while (leftIndex < appliances.size()) {
                appliances.get(leftIndex).setVoltage(0, in);
                if(appliances.get(leftIndex).getResistance() != -1){
                    appliances.get(leftIndex).setVoltage(1, in);
                }
                else{
                    break;
                }
                leftIndex++;
            }

            // 从右边开始，直到遇到断路电器
            while (rightIndex > leftIndex) {
                appliances.get(rightIndex).setVoltage(1, out);
                if(appliances.get(rightIndex).getResistance() != -1){
                    appliances.get(rightIndex).setVoltage(0, out);
                }
                else{
                    break;
                }
                rightIndex--;
            }

            for(ElecticalAppliance appliance : appliances){
                appliance.run();
            }

            return;
        }

        // 电源调节器处理
        boolean hasVCC = false;
        for (ElecticalAppliance appliance : appliances) {
            if (appliance instanceof VCC) {
                hasVCC = true;
            } else if (hasVCC && appliance instanceof ContinuousRegulator) {
                in = in * ((ContinuousRegulator) appliance).getRate();
            } else if (hasVCC && appliance instanceof SteppedRegulator) {
                in = in * ((SteppedRegulator) appliance).getRate();
            } else
                break;
        }

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