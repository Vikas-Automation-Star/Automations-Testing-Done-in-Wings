package util;

import com.wings.utils.APIClient;
import io.appium.java_client.windows.WindowsDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static com.wings.utils.JsonUpdater.updateJson;


public class APITest {
    String file = "./src/test/resources/APILogin.json";
    String file1 = "./src/test/resources/APIGetPostingData.json";

    @Test
    public void apiTest() throws IOException {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//
//        var response = APIClient.callApi("POST", "http://10.10.10.90:8080/WingsApi/api/Login/SignIn", file,headers);
//        String userID=response.getBody().jsonPath().getString("UserId");
//        String sessionId=response.getBody().jsonPath().getString("SessionId");
//        System.out.println("UserId "+userID);
//        System.out.println("SessionId "+sessionId);
//        Assert.assertTrue(response.getStatusCode() < 300, "API returned error: " + response.getStatusLine());
        var response= APIClient.APIAuth(file);
        System.out.println(response.getBody().jsonPath().getString("UserId"));
        System.out.println(response.getBody().jsonPath().getString("SessionId"));

//        Map<String, String> headers1 = new HashMap<>();
//        headers1.put("Content-Type", "application/json");
//        headers1.put("userId",userID);
//        headers1.put("sessionId", sessionId);
        String content = new String(Files.readAllBytes(Paths.get(file1)));
//        String updatedJson = updateJson(content, "userId", APIClient.userID);
//        String updatedJson1 = updateJson(updatedJson, "sessionId", APIClient.sessionId);

        Map<String,Object> map=new HashMap<>();
        map.put("userId",response.getBody().jsonPath().getString("UserId"));
        map.put("SessionId",response.getBody().jsonPath().getString("SessionId"));

        String updatedJson1 = updateJson(content, "sessionId", APIClient.sessionId);


        String filePath = "./output/UpdatedJSONVoucher.json";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            // Write JSON to file
            fileWriter.write(updatedJson1);
            System.out.println("JSON file written successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        var response1 = APIClient.callApi("POST", "http://10.10.10.90:8080/WingsApi/api/Process/Handler", filePath);
        System.out.println("Response "+response1.getBody().jsonPath().getString("dataSet.tables[5].columns[4].name"));
        System.out.println("Response "+response1.getBody().jsonPath().getString("dataSet.tables[5].rows[4][4]"));

//        Assert.assertTrue(response.getStatusCode() < 300, "API returned error: " + response.getStatusLine());
    }

}
