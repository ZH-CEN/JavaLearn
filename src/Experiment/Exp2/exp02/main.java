package Experiment.Exp2.exp02;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        // 创建一个Scanner对象用于输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入年份、月份和日期
        System.out.println("请输入年份：");
        int year = scanner.nextInt();

        System.out.println("请输入月份：");
        int month = scanner.nextInt();

        System.out.println("请输入日期：");
        int day = scanner.nextInt();

        // 使用LocalDate类创建日期对象
        LocalDate date = LocalDate.of(year, month, day);

        // 获取这一天是星期几
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // 输出结果
        System.out.println("这一天是：" + dayOfWeek);
    }
}
