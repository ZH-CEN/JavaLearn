package Experiment.Exp1;
import java.util.Scanner;


public class Dynamicarray {

    static int i = 0;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        int a[] = new int[1];
        while(i<10)
        {
            a=method(a);
        }
    }

    static int[] method(int[] L) {
        double LOGBASE = 1.5; // 设置底数，控制增长率

        if(sc.hasNext()) {
            int num = sc.nextInt();
            int newlength = L.length; // 初始化为当前数组长度

            // 检查是否需要扩大数组
            if(L.length<i+1) {
                newlength = (int) Math.pow(LOGBASE, Math.ceil(Math.log(L.length+1) / Math.log(LOGBASE)));
                int[] newList = new int[newlength]; // 按需要指数级增大数组的内存空间

                // 复制旧数组到新数组中
                for(int j = 0; j < L.length; j++) {
                    newList[j] = L[j];
                }

                newList[L.length] = num; // 新数组的末尾添加新元素
                i++;

                System.out.println("当前数组总空间为：" + newlength + "\n剩余空间为：" + (newlength - L.length - 1));
                printList(newList, i);
                return newList; // 返回新数组
            } else {
                L[i++] = num; // 不需要扩展时直接赋值
                printList(L, i);
                return L; // 返回旧数组
            }
        }
        return L; // 当没有输入时返回原数组
    }


    static void printList(int[] L,int n)
    {
        System.out.println("当前数组为：");
        for(int i=0;i<n;i++)
            System.out.printf(" "+L[i]);
        System.out.println(" ");
    }

}