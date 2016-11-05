
package code2040;

import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;

public class Code2040 {
   private static final String CONTENT = "application/json";
    public static void main(String[] args) throws Exception{
        String urlString = "http://challenge.code2040.org/api/register";
         JSONObject json = new JSONObject();
         json.put("token", "9be19107d85a1e9a2ab2912e6d0c2e95");
         json.put("github", "https://github.com/SamGearou/Code2040.git");
         URL url = new URL(urlString);
         HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
         con.setRequestMethod("POST");
         con.setRequestProperty("Content-Type", CONTENT);
    }
}
