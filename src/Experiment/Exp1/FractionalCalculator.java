package Experiment.Exp1;

import java.util.Scanner;
/**
 * 分数的四则运算测试类
 */
public class FractionalCalculator {
    public static void main(String[] args) {
        Calculator cal = new Calculator();

        // 输入分数内容和运算符
        Scanner input = new Scanner(System.in);
        System.out.println("请分部输入表达式");
        System.out.println("请输入参数1的分子：");
        String data1 = input.next();
        System.out.println("请输入参数1的分母：");
        String data2 = input.next();
        System.out.println("请输入运算符(+-*/)  ：");
        String operation = input.next();
        System.out.println("请输入参数2的分子：");
        String data3 = input.next();
        System.out.println("请输入参数2的分母：");
        String data4 = input.next();

        // 运算并输出
        System.out.println("运算结果为:");
        cal.compute(data1,data2, operation,data3,data4);
    }
}
/**
 * 分数的四则运算类输入：两个以字符串构成的分数，输出其相加的结果，结果仍以分数行式表示。
 */

class Calculator {

    /**设置分子分母
     * 输入：正整数
     * 输出：最简分数式，分子分母不包含公约数
     */
    int numerator;  // 分子
    int denominator; // 分母

    void setNumeratorAndDenominator(int a, int b){  // 设置分子和分母
        int c = gcd(a,b);         // 计算最大公约数
        numerator = a / c;
        denominator = b / c;
    }

    int getNumerator(){
        return numerator;
    }

    int getDenominator(){
        return denominator;
    }
    /*greatest common divisor 求两整数最大公约数，
     * 输入：两正整数a,b
     * 输出：最大公约数b
     */
    int gcd(int a,int b){
        if(a < b){
            int c = a;
            a = b;
            b = c;
        }
        int r = a % b;
        while(r != 0){//此处设置断点，观察并记录a 、b、r三个变量循环过程中的参数变化过程并记录在实验报告中。
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }

    Calculator add(Calculator r){  // 加法运算
        int a = r.getNumerator();
        int b = r.getDenominator();
        int newNumerator = numerator * b + denominator * a;
        int newDenominator = denominator * b;
        Calculator result = new Calculator();
        result.setNumeratorAndDenominator(newNumerator, newDenominator);
        return result;
    }

    Calculator sub(Calculator r){  // 减法运算
        int a = r.getNumerator();
        int b = r.getDenominator();
        int newNumerator = numerator * b - denominator * a;
        int newDenominator = denominator * b;
        Calculator result = new Calculator();
        result.setNumeratorAndDenominator(newNumerator, newDenominator);
        return result;
    }

    Calculator multi(Calculator r){  // 乘法运算
        int a = r.getNumerator();
        int b = r.getDenominator();
        int newNumerator = numerator * a;
        int newDenominator = denominator * b;
        Calculator result = new Calculator();
        result.setNumeratorAndDenominator(newNumerator, newDenominator);
        return result;
    }

    Calculator div(Calculator r){  // 除法运算
        int a = r.getNumerator();
        int b = r.getDenominator();
        int newNumerator = numerator * b;
        int newDenominator = denominator * a;
        Calculator result = new Calculator();
        result.setNumeratorAndDenominator(newNumerator, newDenominator);
        return result;
    }

    // 转化输入格式，判断并输出运算结果，代码中仅包含加法运算的内容，其他运算不用补齐，调试程序时注意只调试加法运算。
    public static void compute(String data1,String data2,String operation,String data3,String data4){

        int data1_1 = (int)Math.abs(Long.parseLong(data1));
        int data1_2 = (int)Math.abs(Long.parseLong(data2));
        int data2_1 = (int)Math.abs(Long.parseLong(data3));
        int data2_2 = (int)Math.abs(Long.parseLong(data4));

        Calculator r1 = new Calculator();
        r1.setNumeratorAndDenominator(data1_1, data1_2);
        Calculator r2 = new Calculator();
        r2.setNumeratorAndDenominator(data2_1, data2_2);

        Calculator result;
        int a,b;
        if(operation.equals("+")){
            result = r1.add(r2);
            a = result.getNumerator();
            b = result.getDenominator();
            System.out.println(data1+"/"+data2+" "+operation+" " +data3+"/"+data4+" = " + a + "/" + b);
        }
        if(operation.equals("-")){
            result = r1.sub(r2);
            a = result.getNumerator();
            b = result.getDenominator();
            System.out.println(data1+"/"+data2+" "+operation+" " +data3+"/"+data4+" = " + a + "/" + b);
        }
        if(operation.equals("*")){
            result = r1.multi(r2);
            a = result.getNumerator();
            b = result.getDenominator();
            System.out.println(data1+"/"+data2+" "+operation+" " +data3+"/"+data4+" = " + a + "/" + b);
        }
        if(operation.equals("/")){
            result = r1.div(r2);
            a = result.getNumerator();
            b = result.getDenominator();
            System.out.println(data1+"/"+data2+" "+operation+" " +data3+"/"+data4+" = " + a + "/" + b);
        }

    }
}