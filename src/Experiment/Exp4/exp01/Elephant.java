package Experiment.Exp4.exp01;

public class Elephant extends Animal {
    private String name;
    double inIceBoxTime = 0;


    public Elephant(String name, double weight) {
        super(name, weight);
        this.attack = 0;
    }

    @Override
    void bark() {
        System.out.println(this.name + "大象：咆哮一声");
    }

}