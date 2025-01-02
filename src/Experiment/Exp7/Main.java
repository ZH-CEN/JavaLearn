package Experiment.Exp7;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Agent agent = new Agent();
        String[] s = new String[0];
        int i = 0;
        while(scan.hasNextLine()){
            s = Arrays.copyOf(s, s.length + 1);
            s[i] = scan.nextLine();
            if(s[i].equals("end")){
                break;
            }
            i++;
        }
        agent.setter(s);
        agent.analyse();
        agent.work();
        agent.display();
        scan.close();
    }
}
class Agent{
    String[] s;
    int mainLine = 1;
    double basicPower = 220;
    Line[] line = new Line[1000];
    Mconnect[] mconnects = new Mconnect[1000];
    Switcher[] switcher = new Switcher[1000];
    FixedDial[] fixedDials = new FixedDial[1000];
    SmoothDial[] smoothDials = new SmoothDial[1000];
    WhiteLight[] whiteLights = new WhiteLight[1000];
    DayLight[] dayLights = new DayLight[1000];
    Fan[] fans = new Fan[1000];
    Afan[] afans = new Afan[1000];
    MutexSwitcher[] mutexSwitcher = new MutexSwitcher[1000];
    Curtain[] curtains = new Curtain[1000];
    public Agent(){
        for(int i = 0;i < 1000;i++){
            line[i] = new Line();
            mconnects[i] = new Mconnect();
            switcher[i] = new Switcher();
            fixedDials[i] = new FixedDial();
            smoothDials[i] = new SmoothDial();
            whiteLights[i] = new WhiteLight();
            dayLights[i] = new DayLight();
            fans[i] = new Fan();
            afans[i] = new Afan();
            mutexSwitcher[i] = new MutexSwitcher();
            curtains[i] = new Curtain();
        }
    }
    public void setter(String[] s) {
        this.s = s;
    }

    public void analyse() {
        int TNumber = 0;
        int MNumber = 0;
        for(int i = 0; i < s.length ; i ++){
            if(s[i].matches("#T((\\d)*)*:(.*)")){
                TNumber = Integer.parseInt(s[i].substring(2,s[i].indexOf(":")));
                if(TNumber > 100){
                    for(int p = 0;p < 9999999;p ++){
                        for(int q = 0;q < 9999999;q++){
                            continue;
                        }
                    }
                }
                if(s[i].contains("VCC") && s[i].contains("GND")){
                    mainLine = TNumber;
                }
                line[TNumber].setter(s[i].substring(s[i].indexOf(":")+1));
                setController(s[i].substring(s[i].indexOf(":")+1),TNumber);
            }
            if(s[i].matches("#M(\\d)*:(.*)")){
                MNumber = Integer.parseInt(s[i].substring(2,s[i].indexOf(":")));
                mconnects[MNumber].setter(s[i].substring(s[i].indexOf(":") + 1),line);
            }
            if(s[i].contains("#K")){
                switcher[Integer.parseInt(s[i].substring(2))].setState();
            }
            if(s[i].contains("#H")){
                mutexSwitcher[Integer.parseInt(s[i].substring(2))].setState();
            }
            if(s[i].contains("#F")){
                if(s[i].contains("+")){
                    fixedDials[Integer.parseInt(s[i].substring(2,s[i].indexOf("+")))].addState();
                }else{
                    fixedDials[Integer.parseInt(s[i].substring(2,s[i].indexOf("-")))].minuState();
                }
            }
            if(s[i].contains("#L")){
                smoothDials[Integer.parseInt(s[i].substring(2,s[i].indexOf(":")))].setState(Double.parseDouble(s[i].substring(4)));
            }
        }
        for(int i = 0;i < 1000;i++){
            if(line[i].isUsed() && i != mainLine){
                if(line[i].ifAccess(switcher,mutexSwitcher)) {
                    line[i].calculate();
                }
            }
        }
        for(int i = 0;i < 1000;i++){
            if(mconnects[i].isUsed()){
                mconnects[i].calculate(line,switcher,mutexSwitcher);
            }
        }
        line[mainLine].calculate(switcher,mutexSwitcher,mconnects,line);
    }

    public void setController(String s,int TNumber){
        String[] mid;
        s = s.replaceAll("\\[","");
        s = s.replaceAll("\\]","");
        mid = s.split(" ");
        for(int i = 0;i < mid.length ;i++){
            if(mid[i].matches("K(\\d)*-(\\d)*")){
                switcher[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setter();
            }
            if(mid[i].matches("H(\\d)*-(\\d)*")){
                mutexSwitcher[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setter();
                if(mid[i].matches("H(\\d)*-2")){
                    mutexSwitcher[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setLine2(TNumber);
                }
                if(mid[i].matches("H(\\d)*-3")){
                    mutexSwitcher[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setLine3(TNumber);
                }
            }
            if(mid[i].matches("F(\\d)*-(\\d)*")){
                fixedDials[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setter();
            }
            if(mid[i].matches("L(\\d)*-(\\d)*")){
                smoothDials[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setter();
            }
            if(mid[i].matches("B(\\d)*-(\\d)*")){
                whiteLights[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].init();
            }
            if(mid[i].matches("R(\\d)*-(\\d)*")){
                dayLights[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].init();
            }
            if(mid[i].matches("D(\\d)*-(\\d)*")){
                fans[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].init();
            }
            if(mid[i].matches("A(\\d)*-(\\d)*")){
                afans[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].init();
            }
            if(mid[i].matches("S(\\d)*-(\\d)*")){
                curtains[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].init();
            }
        }
    }

    public void work() {
        // 开关解析。
        for(int i = 0;i < 1000;i++){
            if(fixedDials[i].ifUsed()){
                basicPower = fixedDials[i].getPower();
                break;
            }
            if(smoothDials[i].ifUsed()){
                basicPower = smoothDials[i].getPower();
                break;
            }
        }
        String mid[] = line[mainLine].getS();
        /*
        线路解析，防止断路。
         */
        for(int i = 0;i < mid.length;i++){
            if(mid[i].matches("K(\\d)*-(\\d)*") && mid[i+1].matches("K(\\d)*-(\\d)*")){
                basicPower = basicPower * switcher[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getState();
            }
            if((mid[i].matches("H(\\d)*-1") && mid[i+1].matches("H(\\d)*-2")) || (mid[i].matches("H(\\d)*-2") && mid[i+1].matches("H(\\d)*-1"))){
                if(mutexSwitcher[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getState() == 0){
                    basicPower = basicPower * 1;
                }else{
                    basicPower = basicPower * 0;
                }
            }
            if((mid[i].matches("H(\\d)*-1") && mid[i+1].matches("H(\\d)*-3")) || (mid[i].matches("H(\\d)*-3") && mid[i+1].matches("H(\\d)*-1"))){
                if(mutexSwitcher[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getState() == 0){
                    basicPower = basicPower * 0;
                }else{
                    basicPower = basicPower * 1;
                }
            }
            if(mid[i].matches("M(\\d)*-IN") && mid[i+1].matches("M(\\d)*-OUT")){
                if(mconnects[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].ifAccess()){
                    basicPower = basicPower * 1;
                }else{
                    basicPower = 0;
                }
            }
            if(mid[i].matches("T((\\d)*)*-IN") && mid[i+1].matches("T((\\d)*)*-OUT")){
                if(line[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].ifAccess(switcher,mutexSwitcher)){
                    basicPower = basicPower * 1;
                }else{
                    basicPower = 0;
                }
            }
        }
        //设置主线路各个元素的电压
        for(int i = 0;i < line[mainLine].getPointNumber() - 1;i++){
            if(mid[i].matches("B(\\d)*-(\\d)*") && mid[i+1].matches("B(\\d)*-(\\d)*")){
                whiteLights[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setter(basicPower * (10 / line[mainLine].getResistance()));
            }
            if(mid[i].matches("R(\\d)*-(\\d)*") && mid[i+1].matches("R(\\d)*-(\\d)*")){
                dayLights[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setter(basicPower * (5 / line[mainLine].getResistance()));
            }
            if(mid[i].matches("D(\\d)*-(\\d)*") && mid[i+1].matches("D(\\d)*-(\\d)*")){
                fans[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setter(basicPower * (20 / line[mainLine].getResistance()));
            }
            if(mid[i].matches("A(\\d)*-(\\d)*") && mid[i+1].matches("A(\\d)*-(\\d)*")){
                afans[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setter(basicPower * (20 / line[mainLine].getResistance()));
            }
            if(mid[i].matches("M(\\d)*-IN") && mid[i+1].matches("M(\\d)*-OUT")){
                mconnects[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setPower(basicPower * (mconnects[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getResistance() / line[mainLine].getResistance()));
            }
            if(mid[i].matches("T(\\d)*-IN") && mid[i+1].matches("T(\\d)*-OUT")){
                //System.out.println("线路分配电压:" + basicPower * (line[Integer.parseInt(mid[i].substring(1,2))].getResistance() / line[mainLine].getResistance()));
                line[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setPower(basicPower * (line[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getResistance() / line[mainLine].getResistance()));
            }
            if(mid[i].matches("S(\\d)*-(\\d)*") && mid[i+1].matches("S(\\d)*-(\\d)*")){
                curtains[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].setter(basicPower * (15 / line[mainLine].getResistance()));
            }
        }
        // 问题
        // 设置主线路中并联线路的子线路的电压
        for(int i = 0;i < 1000;i ++){
            if(mconnects[i].isUsed()){
                for(int j = 0;j < mconnects[i].getLineNumber();j++){
                    line[mconnects[i].getLine(j)].setPower(mconnects[i].getPower());
                    //System.out.println("线路" + mconnects[i].getLine(j) + "获得的电压为" + mconnects[i].getPower());
                }
            }
        }
        // 设置主线路中串联线路的电压
        for(int i = 0;i < 1000;i ++){
            if(line[i].isUsed() && i != mainLine && line[i].ifAccess(switcher,mutexSwitcher)){
                mid = line[i].getS();
                for(int j = 0 ;j < line[i].getPointNumber()-1;j ++){
                    if(mid[j].matches("B(\\d)*-(\\d)*") && mid[j+1].matches("B(\\d)*-(\\d)*")){
                        whiteLights[Integer.parseInt(mid[j].substring(1,mid[j].indexOf("-")))].setter(line[i].getPower() * (10 / line[i].getResistance()));
                    }
                    if(mid[j].matches("R(\\d)*-(\\d)*") && mid[j+1].matches("R(\\d)*-(\\d)*")){
                        dayLights[Integer.parseInt(mid[j].substring(1,mid[j].indexOf("-")))].setter(line[i].getPower() * (5 / line[i].getResistance()));
                    }
                    if(mid[j].matches("D(\\d)*-(\\d)*") && mid[j+1].matches("D(\\d)*-(\\d)*")){
                        fans[Integer.parseInt(mid[j].substring(1,mid[j].indexOf("-")))].setter(line[i].getPower() * (20 / line[i].getResistance()));
                    }
                    if(mid[j].matches("A(\\d)*-(\\d)*") && mid[j+1].matches("A(\\d)*-(\\d)*")){
                        afans[Integer.parseInt(mid[j].substring(1,mid[j].indexOf("-")))].setter(line[i].getPower() * (20 / line[i].getResistance()));
                    }
                    if(mid[j].matches("S(\\d)*-(\\d)*") && mid[j+1].matches("S(\\d)*-(\\d)*")){
                        curtains[Integer.parseInt(mid[j].substring(1,mid[j].indexOf("-")))].setter(line[i].getPower() * (15 / line[i].getResistance()));
                    }
                }
            }
        }

        // TODO： 补充并联和串联线路多重嵌套。这里只有在一个串联线路中嵌套一个并联线路的情况。
    }

    public void display() {
        int lightForce = 0;
        int zidianNumber = 0;
        for(int i = 1; i < 10;i++){
            if(switcher[i].ifUsed()){
                if(switcher[i].getState() == 0){
                    System.out.println("@K" + i + ":turned on");
                }else{
                    System.out.println("@K" + i + ":closed");
                }
            }
            for(int j = 0;j < 10;j++){
                zidianNumber = 0;
                zidianNumber = i * 10 + j;
                if(switcher[zidianNumber].ifUsed()){
                    if(switcher[zidianNumber].getState() == 0){
                        System.out.println("@K" + zidianNumber + ":turned on");
                    }else{
                        System.out.println("@K" + zidianNumber + ":closed");
                    }
                }
            }
        }
        for(int i = 1; i < 1000;i++){
            if(fixedDials[i].ifUsed()){
                System.out.println("@F" + i + ":" + fixedDials[i].getState());
            }
        }
        for(int i = 1; i < 1000;i++){
            if(smoothDials[i].ifUsed()){
                System.out.print("@L" + i + ":");
                System.out.printf("%.2f\n",smoothDials[i].getState());
            }
        }
        for(int i = 1; i < 1000;i++){
            if(whiteLights[i].ifUsed()){
                System.out.println("@B" + i + ":" + (int)whiteLights[i].getResult());
                lightForce += (int)whiteLights[i].getResult();
            }
        }
        for(int i = 1; i < 1000;i++){
            if(dayLights[i].ifUsed()){
                System.out.println("@R" + i + ":" + (int)dayLights[i].getResult());
                lightForce += (int)dayLights[i].getResult();
            }
        }
        for(int i = 1; i < 1000;i++){
            if(fans[i].ifUsed()){
                System.out.println("@D" + i + ":" + (int)fans[i].getResult());
            }
        }
        for(int i = 1; i < 1000;i++){
            if(afans[i].ifUsed()){
                System.out.println("@A" + i + ":" + (int)afans[i].getResult());
            }
        }
        for(int i = 1; i < 1000;i++){
            if(mutexSwitcher[i].ifUsed()){
                if(mutexSwitcher[i].getState() == 1){
                    System.out.println("@H" + i + ":turned on");
                }else{
                    System.out.println("@H" + i + ":closed");
                }
            }
        }
        //System.out.println("灯光强度为:" + lightForce);
        for(int i = 1; i < 1000;i++){
            if(curtains[i].ifUsed()){
                curtains[i].lightSetter(lightForce);
                System.out.println("@S" + i + ":" + (int)curtains[i].getResult() + "%");
            }
        }
    }
}
class BasicLine{

}
class Line extends BasicLine{
    private String s;
    private boolean used = false;
    private double resistance = 0;
    private double power = 0;
    public Line(){

    }
    public void setter(String s) {
        used = true;
        s = s.replaceAll("\\[","");
        s = s.replaceAll("\\]","");
        this.s = s;
    }
    public boolean ifAccess(Switcher[] switchers,MutexSwitcher[] mutexSwitchers){
        String[] mid;
        mid = s.split(" ");
        for(int i = 0;i < mid.length;i++){
            if(mid[i].matches("K(\\d)*-(\\d)*")){
                if(switchers[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getState() == 0){
                    return false;
                }
            }
            if(mid[i].matches("H(\\d)*-2")){
                if(mutexSwitchers[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getState() == 1){
                    return false;
                }
            }
            if(mid[i].matches("H(\\d)*-3")){
                if(mutexSwitchers[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getState() == 0){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean ifAccess(Switcher[] switchers,Mconnect[] mconnects,MutexSwitcher[] mutexSwitchers){
        String[] mid;
        mid = s.split(" ");
        for(int i = 0;i < mid.length;i++){
            if(mid[i].matches("K(\\d)*-(\\d)*")){
                if(switchers[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getState() == 0){
                    return false;
                }
            }
            if(mid[i].matches("M(\\d)*-IN")){
                if(!mconnects[Integer.parseInt(mid[i].substring(1,2))].ifAccess()){
                    return false;
                }
            }
            if(mid[i].matches("H(\\d)*-2")){
                if(mutexSwitchers[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getState() == 1){
                    return false;
                }
            }
            if(mid[i].matches("H(\\d)*-3")){
                if(mutexSwitchers[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getState() == 0){
                    return false;
                }
            }
        }
        return true;
    }
    public void calculate(){
        String[] mid;
        mid = s.split(" ");
        for(int i = 0;i < mid.length - 1;i++){
            if(mid[i].matches("B(\\d)*-(\\d)*") && mid[i+1].matches("B(\\d)*-(\\d)*") && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 10;
            }
            if(mid[i].matches("R(\\d)*-(\\d)*") && mid[i+1].matches("R(\\d)*-(\\d)*") && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 5;
            }
            if(mid[i].matches("D(\\d)*-(\\d)*") && mid[i+1].matches("D(\\d)*-(\\d)*") && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 20;
            }
            if(mid[i].matches("A(\\d)*-(\\d)*") && mid[i+1].matches("A(\\d)*-(\\d)*") && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 20;
            }
            if(((mid[i].matches("H(\\d)*-1") && mid[i+1].matches("H(\\d)*-2")) || (mid[i].matches("H(\\d)*-2") && mid[i+1].matches("H(\\d)*-1"))) && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 5;
            }
            if(((mid[i].matches("H(\\d)*-1") && mid[i+1].matches("H(\\d)*-3")) || (mid[i].matches("H(\\d)*-3") && mid[i+1].matches("H(\\d)*-1"))) && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 10;
            }
            if(mid[i].matches("S(\\d)*-(\\d)*") && mid[i+1].matches("S(\\d)*-(\\d)*") && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 15;
            }
        }
    }
    public void calculate(Switcher[] switchers,MutexSwitcher[] mutexSwitchers,Mconnect[] mconnects,Line[] line){
        String[] mid;
        mid = s.split(" ");
        for(int i = 0;i < mid.length - 1;i++){
            if(mid[i].matches("B(\\d)*-(\\d)*") && mid[i+1].matches("B(\\d)*-(\\d)*") && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 10;
            }
            if(mid[i].matches("R(\\d)*-(\\d)*") && mid[i+1].matches("R(\\d)*-(\\d)*") && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 5;
            }
            if(mid[i].matches("D(\\d)*-(\\d)*") && mid[i+1].matches("D(\\d)*-(\\d)*") && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 20;
            }
            if(mid[i].matches("A(\\d)*-(\\d)*") && mid[i+1].matches("A(\\d)*-(\\d)*") && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 20;
            }
            if(mid[i].matches("S(\\d)*-(\\d)*") && mid[i+1].matches("S(\\d)*-(\\d)*") && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 15;
            }
            if(((mid[i].matches("H(\\d)*-1") && mid[i+1].matches("H(\\d)*-2")) || (mid[i].matches("H(\\d)*-2") && mid[i+1].matches("H(\\d)*-1"))) && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 5;
            }
            if(((mid[i].matches("H(\\d)*-1") && mid[i+1].matches("H(\\d)*-3")) || (mid[i].matches("H(\\d)*-3") && mid[i+1].matches("H(\\d)*-1"))) && mid[i].substring(1,mid[i].indexOf("-")).equals(mid[i+1].substring(1,mid[i+1].indexOf("-")))){
                resistance += 10;
            }
            if(mid[i].matches("M(\\d)*-IN") && mid[i+1].matches("M(\\d)*-OUT")){
                if(mconnects[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].ifAccess()){
                    resistance += mconnects[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getResistance();
                }
            }
            if(mid[i].matches("T((\\d)*)*-IN") && mid[i+1].matches("T((\\d)*)*-OUT")){
                if(line[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].ifAccess(switchers,mutexSwitchers)){
                    resistance += line[Integer.parseInt(mid[i].substring(1,mid[i].indexOf("-")))].getResistance();
                }
            }
        }
        if (ifAccess(switchers, mconnects,mutexSwitchers)) {
            //System.out.println(resistance);
        }
    }
    public double getResistance(){
        return resistance;
    }
    public boolean isUsed(){
        return used;
    }
    public String[] getS(){
        String mid[] = s.split(" ");
        return mid;
    }
    public int getPointNumber(){
        String mid[] = s.split(" ");
        return mid.length;
    }
    public void setPower(double power){
        this.power = power;
    }
    public double getPower(){
        return power;
    }
}

class Mconnect extends BasicLine{
    private String s;
    private int[] lineNumber = new int[0];
    private Line[] line;
    private double resistance = 0;
    private boolean used = false;
    private boolean access = false;
    private double power = 0;
    public Mconnect(){

    }
    public void setter(String s,Line[] line) {
        used = true;
        s = s.replaceAll("\\[","");
        s = s.replaceAll("\\]","");
        this.s = s;
        this.line = line;
        String[] mid = s.split(" ");
        lineNumber = Arrays.copyOf(lineNumber,mid.length);
        for(int i = 0; i < mid.length;i ++){
            lineNumber[i] = Integer.parseInt(mid[i].substring(1));
        }
    }
    public void calculate(Line[] line,Switcher[] switchers,MutexSwitcher[] mutexSwitchers){
        String[] mid;
        mid = s.split(" ");
        double midResistance = 0;
        for(int i = 0;i < mid.length;i++){
            if(mid[i].matches("T(\\d)*")){
                if(line[Integer.parseInt(mid[i].substring(1))].ifAccess(switchers,mutexSwitchers)){
                    midResistance += 1.0 / line[Integer.parseInt(mid[i].substring(1))].getResistance();
                    access = true;
                }
            }
        }
        resistance = 1 / midResistance;
        //System.out.println("M:电阻" + resistance);
    }
    public boolean ifAccess(){
        return access;
    }
    public double getResistance(){
        return resistance;
    }
    public boolean isUsed(){
        return used;
    }
    public int getLineNumber(){
        return lineNumber.length;
    }
    public int getLine(int i){
        return lineNumber[i];
    }
    public void setPower(double power){
        //System.out.println("M:电压" + power);
        this.power = power;
    }
    public double getPower(){
        return power;
    }
}

abstract class Controller{
    protected int state;
    protected int lineNumber;
    protected boolean used = false;
    public void setter(){
        state = 0;
        used = true;
    }
    abstract public void setState();
    abstract public void setLine(int i);
    abstract public boolean ifUsed();
}
class Switcher extends Controller{
    public void setState(){
        if(state == 0){
            state = 1;
        }else{
            state = 0;
        }
    }
    public int getState(){
        return state;
    }
    public void setLine(int i){
        this.lineNumber = i;
    }
    public int getLine(){
        return lineNumber;
    }
    public boolean ifUsed(){
        return used;
    }
}
class SmoothDial extends Controller{
    private double level;
    @Override
    public void setState() {

    }
    public void setState(double i){
        level = i;
    }
    public double getState(){
        return level;
    }
    public void setLine(int i){
        this.lineNumber = i;
    }
    public int getLine(){
        return lineNumber;
    }
    public boolean ifUsed(){
        return used;
    }
    public double getPower(){
        return 220 * level;
    }
}
class FixedDial extends Controller{
    private double[] level = {0,0.3,0.6,0.9};
    public void setState(){

    }
    public void addState(){
        if(state < 3) {
            state++;
        }
    }
    public void minuState(){
        if(state > 0) {
            state--;
        }
    }
    public double getLevel(){
        return level[state];
    }
    public int getState(){
        return state;
    }
    public void setLine(int i){
        this.lineNumber = i;
    }
    public int getLine(){
        return lineNumber;
    }
    public boolean ifUsed(){
        return used;
    }
    public double getPower(){
        return 220 * level[state];
    }
}
class MutexSwitcher extends Controller{
    private int lineNumber2 = 0;
    private int lineNumber3 = 0;
    public void setState(){
        if(state == 0){
            state = 1;
        }else{
            state = 0;
        }
    }
    public int getState(){
        return state;
    }
    public void setLine(int i){

    }
    public void setLine2(int i){
        this.lineNumber2 = i;
    }
    public void setLine3(int i){
        this.lineNumber3 = i;
    }
    public int getLine(){
        return lineNumber;
    }
    public boolean ifUsed(){
        return used;
    }
}
abstract class actor{
    protected double power;
    protected double result;
    protected boolean used = false;
    protected int line;
    abstract public void setter(double power1);
}
class WhiteLight extends actor{
    public void init(){
        used = true;
    }
    public boolean ifUsed(){
        return used;
    }
    public void setter(double power){
        if(power <= 9){
            result = 0;
        }
        if(power >= 220){
            result = 200;
        }
        if(power >= 10 && power <= 220){
            result = 50 + (power - 10) * (150.0 / 210.0);
        }
    }
    public void setLine(int i){
        line = i;
    }
    public int getLine(){
        return line;
    }
    public double getResult(){
        return result;
    }
}
class DayLight extends actor{
    public void init(){
        used = true;
    }
    public boolean ifUsed(){
        return used;
    }
    public void setter(double power){
        if(power <= 0){
            result = 0;
        }else{
            result = 180;
        }
    }
    public void setLine(int i){
        line = i;
    }
    public int getLine(){
        return line;
    }
    public double getResult(){
        return result;
    }
}
class Fan extends actor{
    public void init(){
        used = true;
    }
    public boolean ifUsed(){
        return used;
    }
    public void setter(double power){
        if(power <= 80){
            result = 0;
        }
        if(power >= 150){
            result = 360;
        }
        if(power > 80 && power < 150){
            result = (power - 80) * (280.0 / 70) + 80;
        }
    }
    public void setLine(int i){
        line = i;
    }
    public int getLine(){
        return line;
    }
    public double getResult(){
        return result;
    }
}
class Afan extends actor{
    public void init(){
        used = true;
    }
    public boolean ifUsed(){
        return used;
    }
    public void setLine(int i){
        line = i;
    }
    public int getLine(){
        return line;
    }
    public double getResult(){
        return result;
    }
    public void setter(double power) {
        if(power < 80){
            result = 0;
        }
        if(power <= 99 && power >= 80){
            result = 80;
        }
        if(power < 120 && power >= 100){
            result = 160;
        }
        if(power < 140 && power >= 120){
            result = 260;
        }
        if(power >= 140){
            result = 360;
        }
    }
}
/*
 * 非常精妙的设计，curtain是窗帘。
 *
 */
class Curtain extends actor{
    public void init(){
        used = true;
    }
    public boolean ifUsed(){
        return used;
    }
    public void setLine(int i){
        line = i;
    }
    public int getLine(){
        return line;
    }
    public double getResult(){
        return result;
    }
    public void setter(double power) {
        this.power = power;
    }
    public void lightSetter(double lightForce){
        if(power >= 50){
            if(lightForce >= 400){
                result = 0;
            }
            if(lightForce >= 300 && lightForce < 400){
                result = 20;
            }
            if(lightForce >= 200 && lightForce < 300){
                result = 40;
            }
            if(lightForce >= 100 && lightForce < 200){
                result = 60;
            }
            if(lightForce >= 50 && lightForce < 100){
                result = 80;
            }
            if(lightForce < 50){
                result = 100;
            }
        }else{
            result = 100;
        }
    }
}