package com.example.proyectofinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class RequestHandler {

    // argu url of the script and hash for containind data
    public String sendPostRequest(String requesrURL, HashMap<String, String> postDataParams) {
        URL url;
        StringBuilder sb = new StringBuilder(); // store message retrieved from the server

        try {
            url = new URL(requesrURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();

            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                sb = new StringBuilder();
                String response;

                while ((response = br.readLine()) != null) {
                    sb.append(response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public String sendgetRequest(String requestUrl) {
        StringBuilder sb = new StringBuilder();
        try{
            URL url = new URL(requestUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String res;
            while ((res = bufferedReader.readLine()) != null) {
                sb.append(res + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }

    public String sendGetRequestParam(String requestUrl, String id) {
        StringBuilder sb = new StringBuilder();
        try{
            URL url = new URL(requestUrl + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String res;
            while ((res = bufferedReader.readLine()) != null) {
                sb.append(res + "\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }

    public  String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry: params.entrySet()) {
            if (first)
                first = false;
            else
                sb.append("&");
            sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return sb.toString();
    }
}
