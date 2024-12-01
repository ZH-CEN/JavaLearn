package Experiment.Exp4.exp01;

import java.util.Comparator;

public class AnimalComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal a, Animal b) {
        if(a.attack(b, true))
            return -1;
        else if(b.attack(a ,true))
            return  1;
        else
            return 0;
    }
}
