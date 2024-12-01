package Experiment.Exp3.exp01;

import java.util.Scanner;

public class Test {

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("输入冰箱名：");
//        String fridgeName = scanner.next();
//
//        System.out.println("输入冰箱容量（即能装多少头大象）：");
//        int capacity = scanner.nextInt();
//        scanner.nextLine(); // 消耗掉换行符
//
//        IceBox iceBox = new IceBox(fridgeName, capacity);
//
//        System.out.println("请输入所有大象的信息（格式：大象1名字(重量):大象2名字(重量):...）：");
//        String elephantInfo = scanner.nextLine();
//
//        List<Elephant> elephants = parseElephantInfo(elephantInfo);
//
//        for (Elephant elephant : elephants) {
//            elephant.goIceBox(iceBox);
//        }
//
//        iceBox.sortShow(); // 假设 IceBox 类有一个 sortShow 方法来显示排序后的大象信息
//    }
//
//    // 解析大象信息的辅助方法
//    private static List<Elephant> parseElephantInfo(String elephantInfo) {
//        List<Elephant> elephants = new ArrayList<>();
//        String[] elephantPairs = elephantInfo.split(":");
//
//        for (String elephantPair : elephantPairs) {
//            String[] parts = elephantPair.trim().split("\\(");
//            if (parts.length == 2) {
//                String name = parts[0].trim();
//                String weightStr = parts[1].replace(")", "").trim();
//                double height = Double.parseDouble(weightStr);
//                double weight = Double.parseDouble(weightStr);
//                Elephant elephant = new Elephant(name, height, weight); // 假设 Elephant 构造函数接受名字和重量
//                elephants.add(elephant);
////                elephant.goIceBox();
//            } else {
//                System.out.println("大象信息格式错误：" + elephantPair);
//            }
//        }
//
//        return elephants;
//    }
//}


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double height = 0, weight = 0;

        System.out.println("输入冰箱名：");
        String name = "";
        name = scanner.next();

        System.out.println("输入冰箱容量");
        int capacity = 0;
        capacity = scanner.nextInt();

        //Init  IceBox
        IceBox iceBox = new IceBox(name, capacity);


        for (int i = 0; i < capacity; i++) {

            System.out.println("请输入大象名字：");
//            if (scanner.hasNext())
            name = scanner.next();
            System.out.println("请输入大象重量：");
//            if (scanner.hasNextDouble())
            height = scanner.nextDouble();
            System.out.println("请输入大象高度：");
//            if (scanner.hasNextDouble())
            weight = scanner.nextDouble();
            Elephant elephant = new Elephant(name, height, weight);
            elephant.goIceBox(iceBox);
        }

        iceBox.sortshow();

    }
}

/*
midea
3
sa
1
2
as
3
4
q
1
3
 */