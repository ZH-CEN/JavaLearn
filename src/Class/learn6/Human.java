package Class.learn6;

public class Human {
    private String name;
    private int age;
    private int height;
    private int weight;

    Human(String name, int age, int height, int weight) {
        if(age<0 || height<0 || weight<0 || age>=200 || height>=200 || weight>=200) {
            System.out.println("RangeError:You can't be a human like that!Please check!");
        }
        else{
            this.name = name;
            this.age = age;
            this.height = height;
            this.weight = weight;
        }
    }
}
