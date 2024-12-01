package Experiment.Exp4.exp01;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class test {
    private static InputStream originalSystemIn;

    @BeforeAll
    public static void setUp(){
        originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(("美地冰箱 \n 3 \n 小米微波炉 \n 1 \n 海尔洗衣机 \n 2 \n \n").getBytes()));
    }

    @AfterAll
    public static void restoreSystemInStream() {
        System.setIn(originalSystemIn);  // 恢复原始的 System.in
    }

    @Ignore
    @Test
    public void test() {
        Elephant elephant = new Elephant("dada", 12);
        Lion lion = new Lion("gaga", 11);

        lion.attack(elephant);
    }

    @Test
    public void test2() {
        int count = 0; // 动物总数
        Scanner in = new Scanner(System.in);
        System.out.println("请输入冰箱的名字和容量");
        String name = in.next();
        int cap = in.nextInt();
        count += cap;
        ElectricalAppliance iceBox = new IceBox(name, cap);
        System.out.println("请输入微波炉的名字和容量");
        name = in.next();
        cap = in.nextInt();
        count += cap;
        ElectricalAppliance microWaveOven = new MicroWaveOven(name, cap);
        System.out.println("请输入洗衣机的名字和容量");
        name = in.next();
        cap = in.nextInt();
        count += cap;
        ElectricalAppliance washMachine = new WashMachine(name, cap);
        for (int i = 0; i < count; i++) {
            Animal animal = null;
            int r = (int) ((float) Math.random() * 3);
            int w = (int) ((float) Math.random() * 2000);
            animal = switch (r % 3) {
                case 0 -> new Elephant("大象" + i, w);
                case 1 -> new Tiger("老虎" + i, w);
                case 2 -> new Lion("狮子" + i, w);
                default -> animal;
            };
            r = (int) ((float) Math.random() * 3);
            boolean flag = false;
            while(!flag){
                flag = switch (r % 3) {
                    case 0 ->
                            animal.enterElectricalAppliance(washMachine);
                    case 1 ->
                            animal.enterElectricalAppliance(iceBox);
                    case 2 ->
                            animal.enterElectricalAppliance(microWaveOven);
                    default -> false;
                };
            }
        }
        System.out.println("冰箱sortshow");
        iceBox.sortShow();
        System.out.println("微波炉sortshow");
        microWaveOven.sortShow();
        System.out.println("洗衣机sortshow");
        washMachine.sortShow();
    }
}
