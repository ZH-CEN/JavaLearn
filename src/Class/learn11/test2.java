package Class.learn11;

import java.io.*;

public class test2 {
    public static void main(String[] args) {
        String[] name = {"manar", "左辰"};

        try {
            FileWriter fw = new FileWriter("/home/zchen/IdeaProjects/learn/src/Class/learn11/a2.txt");
            PrintWriter printWriter = new PrintWriter(fw);
            for (String j : name) {
                printWriter.println(j);
//                printWriter.write(j);
            }
            printWriter.flush();
            printWriter.close();
            fw.close();
        }
        catch (Exception e) {
            e.printStackTrace();

        }


    }
}
