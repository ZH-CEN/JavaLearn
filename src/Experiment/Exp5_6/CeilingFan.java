package Experiment.Exp5;

public class CeilingFan extends ElecticalAppliance {
    private final char type = 'D';
    private double rate;

    CeilingFan(int number, String deviceName) {
        super(deviceName);
        this.number = number;
    }

    public void display() {
        System.out.println("@" + this.type + this.number + ":" + (int) this.rate);
    }

    void setRate() {
        double voltagediff = getVoltageDiff();

        if (voltagediff <= 150 && voltagediff >= 80) {
            this.rate = (getVoltageDiff() - 80) * (360 - 80)/(150-80) + 80;
        } else if (voltagediff < 80) {
            this.rate = 0;
        } else if (voltagediff > 150) {
            this.rate = 360;
        }
    }

    @Override
    public void run(double voltage) {
        setVoltage(0, voltage);
//        System.out.println("电压差: " + getVoltageDiff());
        setVoltage(1, 0);
        setRate(); // 设置转速, 使本设备运行
        // 递归调用, 使得所有子设备都运行
        display();
        for (ElecticalAppliance child : this.children) {
            child.run(0); //TODO:
        }
    }

}
