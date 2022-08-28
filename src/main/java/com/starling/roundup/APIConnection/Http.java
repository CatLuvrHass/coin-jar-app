package com.starling.roundup.APIConnection;

import com.starling.roundup.Models.Goal;
import org.json.JSONObject;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {

    public static String GetRequest(URL url, String accessToken) throws IOException {
        StringBuilder buffer = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        int status = conn.getResponseCode();

        return getString(buffer, status, conn);
    }

    public static void PutGoalRequest(int totalUpdate, URL url, String accessToken) throws IOException {
        StringBuilder buffer = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);

        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        JSONObject object = new JSONObject();
        object.put("currency", "GBP");
        object.put("minorUnits", totalUpdate);
        JSONObject amount = new JSONObject();
        JSONObject savings = amount.put("amount", object);

        OutputStreamWriter out = new OutputStreamWriter(
                conn.getOutputStream());
        out.write(
                savings.toString()
        );
        out.flush();
        out.close();

        int status = conn.getResponseCode();
        System.out.println("Transfer made");
        // handle bad status
        getString(buffer, status, conn);
    }
    public static String CreateGoalRequest(Goal goal, URL url, String accessToken) throws IOException {
        StringBuilder buffer = new StringBuilder();
        HttpURLConnection conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("PUT");
        conn.setDoOutput(true);
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject targetObj = new JSONObject();
        targetObj.put("currency", goal.getCurrency());
        targetObj.put("minorUnits", goal.getAmount());

        JSONObject goalObj = new JSONObject();
        goalObj.put("name", goal.getName());
        goalObj.put("currency", goal.getCurrency());
        goalObj.put("target", targetObj);

        OutputStreamWriter out = new OutputStreamWriter(
                conn.getOutputStream());
        out.write(
                goalObj.toString()
        );
        out.flush();
        out.close();
        int status = conn.getResponseCode();
        System.out.println("Goal created");
        return getString(buffer,status, conn);
    }

    public static String getString(StringBuilder responseContent, int status, HttpURLConnection conn) throws IOException {
        String line;
        try {
            BufferedReader reader;
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
                responseContent.append("\n");
            }
            reader.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return responseContent.toString();
    }

}
