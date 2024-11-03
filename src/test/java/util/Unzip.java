package util;



import com.wings.utils.FileUtil;

import java.io.IOException;

public class Unzip {
    public static void main(String[] args) throws IOException {
        FileUtil fileUtil=new FileUtil();

        String filename1 ="Build_14046.2.zip";

        String filepath=System.getProperty("user.dir")+"\\downloads\\"+ filename1;
        String target=System.getProperty("user.dir")+"\\downloads\\target";
        fileUtil.unzip(filepath,target);


    }
}
