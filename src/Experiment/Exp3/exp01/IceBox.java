package Experiment.Exp3.exp01;

public class IceBox {
    private String name;
    private int capacity;
    private boolean status=false;
    int hasnum = 0;
    Elephant[] elephants;
//    ArrayList<Elephant> elephants = new ArrayList<>();

    IceBox(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.elephants = new Elephant[capacity];
//        this.status = status;
    }

    public void openDoor(){
        if(!this.status){
            this.status = true;
            System.out.println("冰箱门已打开");
        }
        else
            System.out.println("无需操作，冰箱门保持开启");
    }

    public void closeDoor(){
        if(this.status){
            this.status = false;
            System.out.println("冰箱门已关闭");
        }
        else
            System.out.println("无需操作，冰箱门保持关闭");
    }

    private void swap(Elephant e1, Elephant e2){
        Elephant temp = e1;
        e1 = e2;
        e2 = temp;
    }

    public void sortName() {
        for (int i = 0; i < this.elephants.length - 1; i++) {
            for (int j = 0; j < this.elephants.length - 1 - i; j++) {
                if (this.elephants[j].getName().compareTo(this.elephants[j + 1].getName()) > 0) {
                    swap(this.elephants[j], this.elephants[j + 1]);
                }
            }
        }
    }

    public void sortWeight() {
        for (int i = 0; i < this.elephants.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < this.elephants.length; j++) {
                if (this.elephants[min].getWeight() > this.elephants[j].getWeight()) {
                    min = j;
                }
            }
            swap(this.elephants[i], this.elephants[min]);
        }
    }

    public void sortHeight() {
        for (int i = 0; i < this.elephants.length - 1; i++) {
            for (int j = 0; j < this.elephants.length - 1 - i; j++) {
                if (this.elephants[j].getHeight() < this.elephants[j + 1].getHeight()) {
                    swap(this.elephants[j], this.elephants[j + 1]);
                }
            }
        }
    }

    private void show(){
        System.out.println("IceBox:\nname："+this.name+"capacity:"+this.capacity);

        for (int i = 0; i < this.hasnum; i++) {
            elephants[i].whoAmI();
        }
    }

    public void sortshow(){
        System.out.println("========冒泡排序结果========");
        sortName();
        show();
        System.out.println("========选择排序结果========");
        sortWeight();
        show();
        System.out.println("========插入排序结果========");
        sortHeight();
        show();
    }
}
//package Experiment.Exp3.exp01;
//
//public class IceBox {
//    private String name;
//    private int capacity;
//    private boolean status=false;
//    int hasnum = 0;
//    Elephant[] elephants;
//
//
//    IceBox(String name, int capacity) {
//        this.name = name;
//        this.capacity = capacity;
//        this.elephants = new Elephant[capacity];
//
//    }
//
//    public void openDoor(){
//        if(!this.status){
//            this.status = true;
//            System.out.println("冰箱门已打开");
//        }
//        else
//            System.out.println("无需操作，冰箱门保持开启");
//    }
//
//    public void closeDoor(){
//        if(this.status){
//            this.status = false;
//            System.out.println("冰箱门已关闭");
//        }
//        else
//            System.out.println("无需操作，冰箱门保持关闭");
//    }
//
//
//    private void show(){
//        System.out.println("IceBox:\nname："+this.name+"capacity:"+this.capacity);
//
//        for (int i = 0; i < this.hasnum; i++) {
//            elephants[i].whoAmI();
//        }
//    }
//
//    boolean addElephant(Elephant elephant){
//        this.elephants[this.hasnum++] = elephant;
//        return true;
//    }
//
//    public void sortShow(){
//        Sort sort = new Sort(elephants);
//        System.out.println("========冒泡排序结果========");
//        sort.sortName();
//        show();
//        System.out.println("========选择排序结果========");
//        sort.sortWeight();
//        show();
//        System.out.println("========插入排序结果========");
//        sort.sortHeight();
//        show();
//        System.out.println("========归并排序结果========");
//        sort.sortGuiBing();
//        show();
//    }
//
//}
