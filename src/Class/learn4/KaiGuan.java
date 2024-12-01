package Class.learn4;

public class KaiGuan {
    int status = 0;
    void swithc(Light lgt, String ctrl){
        if(ctrl.equals("on"))
            lgt.status = 1;
        else if(ctrl.equals("off"))
            lgt.status = 0;
    }
}
