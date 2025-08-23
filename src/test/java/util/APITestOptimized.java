package util;
import com.wings.utils.APIClient;

import com.wings.utils.JsonUpdater;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.testng.Assert.assertEquals;

import org.skyscreamer.jsonassert.JSONCompareMode;

public class APITestOptimized {

    private static final String LOGIN_FILE = "./src/test/resources/APILogin.json";
    private static final String POSTING_DATA_FILE = "./src/test/resources/APIGetPostingData.json";

    @BeforeClass
    public void setUp() throws Exception {
        // Authenticate once and store credentials for reuse
        Response loginResponse = APIClient.APIAuth(LOGIN_FILE);
        assertEquals(loginResponse.getStatusCode(), 200, "Login failed!");
        // Verify mandatory fields in login response
        Assert.assertNotNull(APIClient.userID, "UserId is null in login response");
        Assert.assertNotNull(APIClient.sessionId, "SessionId is null in login response");
    }

    @Test
    public void testPostingDataAPI() throws Exception {
        String jsonTemplate = new String(Files.readAllBytes(Paths.get(POSTING_DATA_FILE)));

        List<String> updatedList = Arrays.asList("PV", "11"); // Replace with your desired list

        // Update sessionId and userId dynamically
        Map<String, Object> updateMap = Map.of(
                "$.userId", APIClient.userID,
                "$.sessionId", APIClient.sessionId,
                "$.stringList", updatedList
        );
        String updatedJson = JsonUpdater.updateJson(jsonTemplate, updateMap);

        // Save updated JSON temporarily (optional if APIClient can accept raw JSON)
        String tempFile = "./output/temp_request.json";
        Files.write(Paths.get(tempFile), updatedJson.getBytes());

        Response response = APIClient.callApi("POST",
                "http://10.10.10.90:8080/WingsApi/api/Process/Handler",
                tempFile

        );

        assertStatusCode(response, 200);

        // Example: ignoring JSON array element order in validation
        String expectedJson = "{ \"status\": \"Success\" }"; // replace with your actual expected JSON
        System.out.println(response.asString());
    }

    /** Utility to assert status code */
    private void assertStatusCode(Response response, int expected) {
        assertEquals(response.getStatusCode(), expected, "Unexpected status code: " + response.getStatusCode());
    }

    /** Utility to compare JSON ignoring array element positions */
    private void assertJsonEqualsIgnoreArrayOrder(String expectedJson, String actualJson) throws Exception {
        assertEquals(expectedJson, actualJson, JSONCompareMode.NON_EXTENSIBLE);
    }

}
