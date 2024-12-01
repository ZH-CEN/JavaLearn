package Class.learn4;

public class Fengshan {
    private double speed = 0;
    static double PI = 3.14;

    void span(int rate){
        this.speed = rate*PI;
        if(this.speed != 0)
            System.out.println("风扇以"+this.speed+"速度转！");
        else
            System.out.println("风扇不转！");
    }
}
