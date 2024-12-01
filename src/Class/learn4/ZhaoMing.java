package Class.learn4;

public class ZhaoMing {
    Light light;
    KaiGuan kg;
    int i = 0;
    ZhaoMing(Light light, KaiGuan kg) {
        this.light = light;
        this.kg = kg;
    }

    void swittch(String ctrl){
        if(ctrl.equals("on")){
            i++;
            this.kg.status = 1;
        }
        if(this.kg.status == 1){
            this.light.status = i;
        }
        else
            this.kg.status = 0;

    }
}

