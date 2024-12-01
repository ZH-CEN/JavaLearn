package Class.learn12;

import java.io.*;

public class CopyFile {

    static void copyFile(String srcPath, String destPath, int block) throws IOException {
        BufferedInputStream bis;
        BufferedOutputStream bos;
        try{
            bis = new BufferedInputStream(new FileInputStream(srcPath), block);
            bos = new BufferedOutputStream(new FileOutputStream(destPath), block);

            while(bis.available() != 0){
//                System.out.println(bis.available());
//                bos.write();

//                throw new ;
                System.out.println((char)bis.read());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        copyFile("/home/zchen/IdeaProjects/learn/src/Class/learn11/a2.txt", "/home/zchen/IdeaProjects/learn/src/Class/learn12/a2.txt", 1024);
    }
}
