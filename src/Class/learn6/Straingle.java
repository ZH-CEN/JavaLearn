package Class.learn6;

public class Straingle {
    private double a,b,c;
    public Straingle(double a, double b, double c) {
        if(a+b>c && a+c>b && b+c>a){
            this.a=a;
            this.b=b;
            this.c=c;
        }

    }
    double getArea(){
        //公式
        return 0;
    }

    double getPerimeter(){
        return this.a+this.b+this.c;
    }

    void setlength(double length, int which){
        switch(which){
            case 0: this.a=length; break;
            case 1: this.b=length; break;
            case 2: this.c=length; break;
        }
        if(this.a+this.b > this.c && this.b+this.c > this.a && this.c+this.a > this.b){

        }
    }

}
