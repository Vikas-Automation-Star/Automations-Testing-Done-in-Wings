import com.wings.utils.FileUtil;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class readJsonData {
    public static void main(String[] args) throws IOException, ParseException {
        FileUtil file=new FileUtil();
        String filePath="./src/main/resources/input_Data.json";
        System.out.println(file.getData(filePath,"companyName"));
        System.out.println(file.getData(filePath,"count"));
        System.out.println(file.getData(filePath,"cost"));
        System.out.println(file.getData(filePath,"salary"));
    }

}
 