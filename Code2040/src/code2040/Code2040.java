
package code2040;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import org.json.JSONObject;

//Class that sends and receives HTTP requests
//Sam Gearou
//5 November 2016

public class Code2040 {
   private static final String CONTENT = "application/json";
    public static void main(String[] args) throws Exception{
           
}
    //step 5 solution
    public static void step5() throws Exception{
        String urlString = "http://challenge.code2040.org/api/dating";
        JSONObject json = new JSONObject();
        URL url = new URL(urlString);
         json.put("token", "9be19107d85a1e9a2ab2912e6d0c2e95");
         HttpURLConnection con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("POST");
         con.setRequestProperty("Content-Type", CONTENT);
         
         // Send post request
	 con.setDoOutput(true); //allows you to write data to the URL
         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	 wr.writeBytes(json.toString());
	 wr.flush();
	 wr.close();
         
         //read input
         BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
         StringBuilder builder = new StringBuilder();
         String answer;
         String line;
         while((line = br.readLine()) != null){
             builder.append(line);
         }
         answer = builder.toString();
         builder = new StringBuilder();
         String interval;
         StringBuilder intervalBuilder = new StringBuilder();
         String dateString = "";
         for(int i = 14; i<answer.length()-1; i++){
             if(answer.charAt(i) != '"' && dateString.equals("")){
              builder.append(answer.charAt(i));
              continue;
             }
             if(answer.charAt(i) == '"' && dateString.equals("")){
                dateString = builder.toString();
                i = i+12;
                continue;
             }
                 intervalBuilder.append(answer.charAt(i));
         }
         interval = intervalBuilder.toString();
         Calendar cal = javax.xml.bind.DatatypeConverter.parseDateTime(dateString);
         cal.setTimeInMillis(cal.getTimeInMillis() + (Integer.parseInt(interval) * 1000));
         
         String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));

         if(day.length() == 1){
             day = "0" + day;
         }
         String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
         if(month.length() == 1){
             month = "0" + month;
         }
         String year = String.valueOf(cal.get(Calendar.YEAR));
         String hours = String.valueOf(cal.get(Calendar.HOUR));
         if(hours.length() == 1){
             hours = "0" + hours;
         }
         String mins = String.valueOf(cal.get(Calendar.MINUTE));
         if(mins.length() == 1){
             mins = "0" + mins;
         }
         String secs = String.valueOf(cal.get(Calendar.SECOND));
         if(secs.length() == 1){
             secs = "0" + secs;
         }
         String myAns = year + "-" + month + "-" + day + "T" + hours  + ":" + mins + ":" + secs + "Z";
         JSONObject ans = new JSONObject();
         ans.put("token", "9be19107d85a1e9a2ab2912e6d0c2e95");
         ans.put("datestamp", myAns);
         String newURL = "http://challenge.code2040.org/api/dating/validate";
         url = new URL(newURL);
         con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("POST");
         con.setRequestProperty("Content-Type", CONTENT);
	 con.setDoOutput(true); //allows you to write data to the URL
         wr = new DataOutputStream(con.getOutputStream());
	 wr.writeBytes(ans.toString());
	 wr.flush();
	 wr.close();
         int responseCode = con.getResponseCode();
	 System.out.println("\nSending 'POST' request to URL : " + url);
	 System.out.println("Post parameters : " + ans.toString());
	 System.out.println("Response Code : " + responseCode);
         String response = con.getResponseMessage();
         System.out.println(response);
    }
    
    //step 4 solution
    public static void step4() throws Exception{
        String urlString = "http://challenge.code2040.org/api/prefix";
         JSONObject json = new JSONObject();
         json.put("token", "9be19107d85a1e9a2ab2912e6d0c2e95");
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
         
         BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
         StringBuilder builder = new StringBuilder();
         String answer;
         String line;
         while((line = br.readLine()) != null){
             builder.append(line);
         }
         answer = builder.toString();
         builder = new StringBuilder();
         String prefix = "";
         int index = 0;
         for(int i = 11; i<answer.length(); i++){
             if(answer.charAt(i) != '"' && prefix.equals("")){
              builder.append(answer.charAt(i));
             }
             if(answer.charAt(i) == '"' && prefix.equals("")){
                prefix = builder.toString();
             }
             if(answer.charAt(i) != '['){
                 continue;
             }
             index = i;
             break;
         }
         StringTokenizer st = new StringTokenizer(answer.substring(index), "[]{},\"");
         ArrayList<String> words = new ArrayList<>();
         while(st.hasMoreTokens()){
             String token = st.nextToken();
             if(!token.substring(0, prefix.length()).equals(prefix)){
                 words.add(token);
             }
         }
         String[] myArr = new String[words.size()];
         for(int i = 0; i<words.size(); i++){
             myArr[i] = words.get(i);
         }
         JSONObject ans = new JSONObject();
         ans.put("token", "9be19107d85a1e9a2ab2912e6d0c2e95");
         ans.put("array", myArr);
         String newURL = "http://challenge.code2040.org/api/prefix/validate";
         url = new URL(newURL);
         con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("POST");
         con.setRequestProperty("Content-Type", CONTENT);
         // Send post request
	 con.setDoOutput(true); //allows you to write data to the URL
         wr = new DataOutputStream(con.getOutputStream());
	 wr.writeBytes(ans.toString());
	 wr.flush();
	 wr.close();
         
         int responseCode = con.getResponseCode();
	 System.out.println("\nSending 'POST' request to URL : " + url);
	 System.out.println("Post parameters : " + ans.toString());
	 System.out.println("Response Code : " + responseCode);
         String response = con.getResponseMessage();
         System.out.println(response);
    }
    
    public static void step3() throws Exception{
        String urlString = "http://challenge.code2040.org/api/haystack";
         JSONObject json = new JSONObject();
         json.put("token", "9be19107d85a1e9a2ab2912e6d0c2e95");
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
         
         BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
         StringBuilder builder = new StringBuilder();
         String line;
         String answer;
         while((line = br.readLine()) != null){
             builder.append(line);
         }
         int index = 0;
         answer = builder.toString();
         builder = new StringBuilder();
         String needle = "";
         for(int i = 11; i<answer.length(); i++){
             if(answer.charAt(i) != '"' && needle.equals("")){
              builder.append(answer.charAt(i));
             }
             if(answer.charAt(i) == '"' && needle.equals("")){
                needle = builder.toString();
             }
             if(answer.charAt(i) != '['){
                 continue;
             }
             index = i;
             break;
         }
         int count = 0;
         StringTokenizer st = new StringTokenizer(answer.substring(index), "[]{},\":");
         while(st.hasMoreTokens()){
             if(st.nextToken().equals(needle)){
                 break;
             }
             else{
                 count++;
             }
         }
         
         JSONObject ans = new JSONObject();
         ans.put("token", "9be19107d85a1e9a2ab2912e6d0c2e95");
         ans.put("needle", count);
         String newURL = "http://challenge.code2040.org/api/haystack/validate";
         url = new URL(newURL);
         con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("POST");
         con.setRequestProperty("Content-Type", CONTENT);
         
         // Send post request
	 con.setDoOutput(true); //allows you to write data to the URL
         wr = new DataOutputStream(con.getOutputStream());
	 wr.writeBytes(ans.toString());
	 wr.flush();
	 wr.close();
         
         int responseCode = con.getResponseCode();
	 System.out.println("\nSending 'POST' request to URL : " + url);
	 System.out.println("Post parameters : " + ans.toString());
	 System.out.println("Response Code : " + responseCode);
         String response = con.getResponseMessage();
         System.out.println(response);
    }
    
    public static void step2() throws Exception {
        String urlString = "http://challenge.code2040.org/api/reverse";
         JSONObject json = new JSONObject();
         json.put("token", "9be19107d85a1e9a2ab2912e6d0c2e95");
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
         
         BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
         StringBuilder builder = new StringBuilder();
         String line;
         String answer;
         while((line = br.readLine()) != null){
             builder.append(line);
         }
         answer = builder.reverse().toString();
         
         JSONObject jsonAns = new JSONObject();
         jsonAns.put("token", "9be19107d85a1e9a2ab2912e6d0c2e95");
         jsonAns.put("string", answer);
         String sendHere = "http://challenge.code2040.org/api/reverse/validate";
         url = new URL(sendHere);
         con = (HttpURLConnection) url.openConnection();
         con.setRequestMethod("POST");
         con.setRequestProperty("Content-Type", CONTENT);
         con.setDoOutput(true); //allows you to write data to the URL
         wr = new DataOutputStream(con.getOutputStream());
	 wr.writeBytes(jsonAns.toString());
	 wr.flush();
	 wr.close();
         
         int responseCode = con.getResponseCode();
	 System.out.println("\nSending 'POST' request to URL : " + url);
	 System.out.println("Post parameters : " + jsonAns.toString());
	 System.out.println("Response Code : " + responseCode);
         String response = con.getResponseMessage();
         System.out.println(response);
    }
    
    public static void step1() throws Exception {
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

