package Experiment.Exp5_6;

import java.text.SimpleDateFormat;
import java.util.Date;

abstract public class ElecticalAppliance extends CircuitNode {
    /*
     * 电器类
     */
    protected char type = ' ';
    protected double R = 0;//电阻，低版本电器不需要
    protected double I = 0;//电流，低版本电器不需要
    protected double highestI;//最大电流，低版本电器不需要
    protected boolean outofI = false;//是否超过最大电流，低版本电器不需要
    protected int number;
    protected double[] voltages = new double[2];
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Date date = new Date();

    ElecticalAppliance(String deviceName) {
        super(deviceName);
    }

    ElecticalAppliance() {
    }

    public boolean iscut() {
        return false;
    }

    public abstract void display();

    void log(String[] args) {
        for (int i = 0; i < args.length; i++) {
            date = new Date();
            System.out.println("[LOG::" + sdf.format(date) + "]:\t" + args[i]);
        }
    }

    double getVoltageDiff() {
        if (voltages[0] < voltages[1]) {
            String[] args = new String[1];
            args[0] = "Wrong!: The voltage might set error";
            this.log(args);
        }
        return Math.abs(voltages[0] - voltages[1]);
    }

    double getVoltage(int num) {
        return voltages[num];
    }

    void setVoltage(int num, double voltage) {
        if (num < 0 || num > 1) {
            throw new IllegalArgumentException("num must be between 0 and 1");
        }
        this.voltages[num] = voltage;
    }

    public double getResistance() {
        return this.R;
    }

    void disconnected(int num) {
        if (num < -1 || num > 1) {
            throw new IllegalArgumentException("num must be between 0 and 1");
        }
        if (num == -1) {
            this.voltages[0] = 0;
            this.voltages[1] = 0;
            return;
        }
        this.voltages[num] = 0;
    }

    public abstract void run(double in, double out, double I);
}
//package Experiment.Exp5_6;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//abstract public class ElecticalAppliance extends CircuitNode {
//
//    protected double R;
//    protected int number;
//    protected double[] voltage = new double[2];
//    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    private Date date = new Date();
//
//    ElecticalAppliance(String deviceName) {
//        super(deviceName);
//    }
//
//    public abstract void display();
//
//    void log(String[] args) {
//        for (int i = 0; i < args.length; i++) {
//            date = new Date();
//            System.out.println("[LOG::" + sdf.format(date) + "]:\t" + args[i]);
//        }
//    }
//
//    double getVoltageDiff() {
//        if (voltage[0] < voltage[1]) {
//            String[] args = new String[1];
//            args[0] = "Wrong!: The voltage might set error";
//            this.log(args);
//        }
//        return Math.abs(voltage[0] - voltage[1]);
//    }
//
//    double getVoltage(int num) {
//        return voltage[num];
//    }
//
//    void setVoltage(int num, double voltage) {
//        if (num < 0 || num > 1) {
//            throw new IllegalArgumentException("num must be between 0 and 1");
//        }
//        this.voltage[num] = voltage;
//    }
//
//    void disconnected(int num) {
//        if (num < -1 || num > 1) {
//            throw new IllegalArgumentException("num must be between 0 and 1");
//        }
//        if (num == -1) {
//            this.voltage[0] = 0;
//            this.voltage[1] = 0;
//            return;
//        }
//        this.voltage[num] = 0;
//    }
//
//    public abstract void run(double voltage);
//}
