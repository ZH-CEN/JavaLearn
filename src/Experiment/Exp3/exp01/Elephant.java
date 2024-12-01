package Experiment.Exp3.exp01;

public class Elephant {
    private String name;
    private double height;
    private double weight;

    double inIceBoxTime = 0;


    Elephant(String name, double height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWeight() {
        return this.weight;
    }

    public void goIceBox(IceBox iceBox) {

        iceBox.elephants[iceBox.hasnum++] = this;
        int time = 0;
        inIceBoxTime++;
    }

    public void whoAmI(){
        System.out.println("Name:"+this.name+"；IceBoxTime:"+this.inIceBoxTime+"；Weight:"+this.weight+"；Height:"+this.height);
    }

}

//package Experiment.Exp3.exp01;
//
//public class Elephant {
//    private String name;
//    private double height;
//    private double weight;
//    static int time = 0;
//    int inIceBoxTime = 0;
//
//    Elephant(String name,double weight) {
//        this.name = name;
//        this.height = 0;
//        this.weight = weight;
//    }
//    Elephant(String name, double height, double weight) {
//        this.name = name;
//        this.height = height;
//        this.weight = weight;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public double getHeight() {
//        return this.height;
//    }
//
//    public double getWeight() {
//        return this.weight;
//    }
//
//    public void goIceBox(IceBox iceBox) {
//
//        iceBox.elephants[iceBox.hasnum++] = this;
//        this.inIceBoxTime = ++time;
//    }
//
//    public void whoAmI(){
//        System.out.println("Name:"+this.name+"；IceBoxTime:"+this.inIceBoxTime+"；Weight:"+this.weight+"；Height:"+this.height);
//    }
//
//}
