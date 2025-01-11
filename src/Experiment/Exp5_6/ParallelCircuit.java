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
        System.out.println("@" + this.deviceName + ":" + Math.round(this.voltages[0]) + "-" + Math.round(this.voltages[1]));
    }

    public boolean iscut() {
        if(this.getResistance() == -1)
            return true;
        return false;
    }

    @Override
    public double getResistance() { // 负责短路和断路解析， R = -1 代表断路， R = 0 代表短路
        double totalResistance = 0;
        boolean flag = false;
        for (ElecticalAppliance appliance : appliances) {
            if(appliance.iscut()){
                flag = true;
                continue;
            }
//            System.out.println(appliance.deviceName + " " + appliance.getResistance());
            if(appliance.getResistance() == 0){ // 短路
                return 0;
            }
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
        run();
    }

    @Override
    public void run() {
        double in = this.getVoltage(0); // 获取输入电压
        double out = this.getVoltage(1); // 获取输出电压
        double I = this.I; // 获取电流

        if(in == out){ // 如果输入电压等于输出电压
            for (ElecticalAppliance appliance : appliances) {
                if (!appliance.iscut()) { // 如果电器没有断路
                    appliance.run(in, out, -1); // 运行电器，电流为-1
                }
                else
                    appliance.run(in, out, 0); // 运行电器，电流为0
            }
            return;
        }

        // 以相同的输入和输出电压运行每个电器
        for (ElecticalAppliance appliance : appliances) {
            if (!appliance.iscut()) { // 如果电器没有断路
                appliance.run(in, out, -1); // 运行电器，电流为-1
            }
            else
                appliance.run(in, 0, 0); // 运行电器，电流为0
        }
    }
}