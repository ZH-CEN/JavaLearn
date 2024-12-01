package Class.learn11;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class test {
    public static void main(String[] args) {
        int[] arr = {1,3,3,4,2,4,5,5,3,5};

        try {
//            String filedirectory = "./al.txt";
//            File file = new File(filedirectory);
            OutputStream outputStream = new FileOutputStream("/home/zchen/IdeaProjects/learn/src/Class/learn11/al.txt");
            DataOutputStream dos = new DataOutputStream(outputStream);
            for (int j : arr) {
                dos.writeInt(j);
            }
        }
        catch (Exception e) {

            e.printStackTrace();
        }


    }
}
