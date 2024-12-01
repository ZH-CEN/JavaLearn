package Experiment.Exp4.exp01;

public class ElectricalAppliance {
    private boolean status = false;
    protected String name;
    protected int capacity;
    protected int hasnum = 0;
    protected Animal[] animals;

    public ElectricalAppliance(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        animals = new Animal[capacity];
    }

    public void openCap() {
        status = true;
        System.out.println(this.name + " is open");
    }

    public void closeCap() {
        status = false; 
        System.out.println(this.name + " is close");
    }

    boolean addAnimal(Animal animal) {
        if (this.hasnum >= animals.length) {
            return false;
        }
        this.animals[this.hasnum++] = animal;
        return true;
    }

    void sortShow(){
        System.out.println("------------");
//        Sort.mergeSort();
        Sort.mergeSort(animals, 0, this.hasnum-1, new AnimalComparator());
        for (int i = 0; i < this.capacity; i++) {
            this.animals[i].whoAmI();
        }
    }
}
