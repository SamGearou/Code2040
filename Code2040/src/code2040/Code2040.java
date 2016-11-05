
package code2040;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Code2040 {
   private static final String CONTENT = "application/json";
    public static void main(String[] args) throws Exception{
        String urlString = "http://challenge.code2040.org/api/register";
         JSONObject json = new JSONObject();
         json.put("token", "9be19107d85a1e9a2ab2912e6d0c2e95");
         json.put("github", "https://github.com/SamGearou/Code2040.git");
         URL url = new URL(urlString);
         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("POST");
         con.setRequestProperty("Content-Type", CONTENT);
         // Send post request
	 con.setDoOutput(true); //allows you to write data to the URL
         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	 wr.writeBytes(json.toString());
	 wr.flush();
	 wr.close();
         
         int responseCode = con.getResponseCode();
	 System.out.println("\nSending 'POST' request to URL : " + url);
	 System.out.println("Post parameters : " + json.toString());
	 System.out.println("Response Code : " + responseCode);
         String response = con.getResponseMessage();
         System.out.println(response);
    }
}
