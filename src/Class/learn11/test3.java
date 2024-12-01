package Class.learn11;

import java.io.*;

public class test3 {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = null;
        Reader reader = null;
        try {
            inputStream = new FileInputStream("/home/zchen/IdeaProjects/learn/src/Class/learn11/a2.txt");
            reader = new InputStreamReader(inputStream);

            while(reader.ready()){
                System.out.println((reader.read()));
            }
        }

        catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }
}
