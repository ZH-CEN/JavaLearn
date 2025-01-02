package Class.learn15;

import java.util.HashSet;


public class test {
    public static void main(String[] args) {

        HashSet<Student> hs1 = new HashSet<>();

        Student a1 = new Student("John", 5);
        Student b1 = new Student("John", 6);
        Student c1 = new Student("Doe", 7);

        hs1.add(a1);
        hs1.add(b1);
        hs1.add(c1);
//        hs.add(d);

        for(Student i : hs1){
            System.out.println(i.name);
        }

    }
}