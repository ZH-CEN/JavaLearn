package Experiment.Exp4.exp01;

public class Animal {
    protected String name;
    protected int attack;
    //    protected int age;
    protected double weight;

    public Animal(String name, double weight) {
        this.name = name;
//        this.age = age;
        this.weight = weight;
    }

    void bark() {
        System.out.println(this.name + "：叫");
    }

    public void killed() {
        System.out.println(this.name + "was killed");
    }

    boolean attack(Animal animal) {
        System.out.println(this.attack);
        System.out.println(animal.attack);
        if (this.attack > animal.attack) {
            if (this.weight > animal.weight/2) {
                animal.killed();
                return true;
            }
            else System.out.println("打不过");
        } else if (this.attack == animal.attack) {
            if (this.weight > animal.weight) {
                animal.killed();
                return true;
            } else
                System.out.println("打不过");

        } else {
            System.out.println("打不过");
        }
        return false;
    }

    boolean attack(Animal animal, boolean flag) {
        if (this.attack > animal.attack) {
            if (this.weight > animal.weight/2) {
                return true;
            }
        } else if (this.attack == animal.attack) {
            if (this.weight > animal.weight) {
                return true;
            }
        }
        return false;
    }

    public void whoAmI() {
        System.out.println("Name:" + this.name + "；Weight:" + this.weight);
    }

    public boolean enterElectricalAppliance(ElectricalAppliance appliance) {
        if (appliance.addAnimal(this)) {
            this.bark();
            return true;
        } else {
            return false;
        }
    }

}
