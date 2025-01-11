package Experiment.Exp5_6;

public class Curtain extends ElecticalAppliance {
    private final char type = 'S';
    private double openRatio;

    Curtain(int number, String deviceName) {
        super(deviceName);
        this.number = number;
        this.R = 15;
        this.highestI = 12;
    }

    public void display() {
        dynamicOpenRatio();
        System.out.println("@" + this.type + this.number + ": " + Math.round(this.openRatio * 100) + "% " + Math.round(this.voltages[0]) + "-" + Math.round(this.voltages[1]) + " " + (I > highestI ?  "exceeding current limit error": ""));
    }

    public void setOpenRatio(double totalLightIntensity) {
        if (this.getVoltageDiff() < 50) {
            this.openRatio = 1.0; // 电压低于50V，窗帘全开
        } else if (totalLightIntensity < 50) {
            this.openRatio = 1.0;
        } else if (totalLightIntensity < 100) {
            this.openRatio = 0.8;
        } else if (totalLightIntensity < 200) {
            this.openRatio = 0.6;
        } else if (totalLightIntensity < 300) {
            this.openRatio = 0.4;
        } else if (totalLightIntensity < 400) {
            this.openRatio = 0.2;
        } else {
            this.openRatio = 0.0; // 光照强度在400lux及以上，窗帘关闭
        }
    }

    @Override
    public void run(double in, double out, double I) {
        setVoltage(0, in);
        setVoltage(1, out);
        this.I = I;
        dynamicOpenRatio();
    }

    @Override
    public void run() {
        dynamicOpenRatio();
    }

    private void dynamicOpenRatio() {
        double totalLightIntensity = 0;
        for (ElecticalAppliance appliance : ShellConsole.devices.values()) {
            if (appliance instanceof Lamp) {
                totalLightIntensity += ((Lamp) appliance).getLightBrightness();
            }
        }
        setOpenRatio(totalLightIntensity);
    }
}