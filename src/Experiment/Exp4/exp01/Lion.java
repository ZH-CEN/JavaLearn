package Experiment.Exp4.exp01;

public class Lion extends Animal {
    private String name;
    double inIceBoxTime = 0;


    public Lion(String name, double weight) {
        super(name, weight);
        this.attack = 1;
    }

    @Override
    void bark() {
        System.out.println(this.name + "狮子：怒吼一声");
    }
}
