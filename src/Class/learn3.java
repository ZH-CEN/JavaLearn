package Class;

public class learn3 {
    public static void main(String[] args){
        Array array = new Array();
        array.init_fact(9);
        array.printArray();
    }
}

class fengshan {
    int size;
    boolean roll(){
        boolean flag = false;
        return flag;
    }


}


class Array{
    int[] array;
    int[] init_fact(int size){
        array = new int[size];

        for (int i = 0; i < size; i++) {
            if(i < 2){
                array[i] = 1;
            }
            else{
                array[i] = array[i-1]+array[i-2];
            }
        }
        return array;
    }

    void printArray(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }
}
