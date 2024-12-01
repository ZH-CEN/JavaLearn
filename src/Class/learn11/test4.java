//package Class.learn11;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStreamReader;
//import java.io.Reader;
//
//public class test4 {
//    public static void main(String[] args) {
//
//        FileInputStream inputStream = null;
//        Reader reader = null;
//        try {
//            inputStream = new FileInputStream("/home/zchen/IdeaProjects/learn/src/Class/learn11/a2.txt");
//            reader = new InputStreamReader(inputStream);
//
//            while(reader.ready()){
//
//                System.out.println((reader.read()));
//            }
//
//        }
//
//        catch (FileNotFoundException e) {
//
//            e.printStackTrace();
//        }
//    }
//}
