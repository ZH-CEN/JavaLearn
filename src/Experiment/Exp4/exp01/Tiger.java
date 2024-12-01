package Experiment.Exp4.exp01;

public class Tiger extends Animal {

    public Tiger(String name, double weight) {
        super(name, weight);
        this.attack = 1;
    }

    @Override
    void bark() {
        System.out.println(this.name + "老虎：怒吼一声");
    }


}
