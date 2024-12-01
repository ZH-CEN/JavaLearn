package Class.learn12;

import java.io.*;
import java.net.URL;

public class WebCode {



    public static void main(String[] args) throws IOException {
        String urlstr = "https://www.baidu.com";
        URL url = new URL(urlstr);
        InputStream is = url.openStream();

        Reader reader = new InputStreamReader(is);

        System.out.println(reader.read());
//        FileInputStream
    }
}
