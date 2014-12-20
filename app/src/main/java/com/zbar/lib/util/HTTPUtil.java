package com.zbar.lib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HTTPUtil {

    static String TAG = "HTTPUtil";

    static JSONObject JSONUpload = null;
    static boolean isJSONUploadValid = false;
    static boolean isUpLoadSuccessful = false;

    public void setJSONUpload(JSONObject json) {
        this.JSONUpload = json;
    }

    public void setIsJSONUploadValid(boolean valid) {
        this.isJSONUploadValid = valid;
    }

    public static void postJSONToServer(JSONObject json, String url) {
        JSONUpload = json;
        isJSONUploadValid = true;
        new PostData().execute(url);
    }

    private static class PostData extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... urls) {
            try {
                postURL(urls[0]);
                return null;
            } catch (IOException e){
                return null;
            }
        }

        @Override
        protected void onPostExecute(Void nonsense) {
            Log.i(TAG, "upload Success");
        }
    }

    public static boolean postURL(String str_url) throws IOException {
        if (!isJSONUploadValid){
            Log.i(TAG, "JSON is not ready before send POST, Abort.");
            return false;
        }

        try {
            URL url = new URL(str_url);
            HttpURLConnection connection  = (HttpURLConnection) url.openConnection();
            String response;

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("IndoorNavigation", "version 0.0.0");
            connection.setRequestProperty("Accept", "Application/json");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(JSONUpload.toString());
            writer.close();

            InputStream reader = connection.getInputStream();
            response = inputStreamToString(reader);
            reader.close();
            if (response.equals("UploadSuccess")) {
                Log.i(TAG, "Upload Success");
                return true;
            } else {
                Log.i(TAG, "Upload Unsuccessful");
                return false;
            }

        } catch (MalformedURLException e){
            Log.i(TAG,"malformed URL");
            return false;
        } catch (IOException e){
            Log.i(TAG, "IOException");
            return false;
        }
    }

    public static String inputStreamToString(InputStream reader) {
        Scanner s = new Scanner(reader).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static boolean CheckNetwork(Context current) {
        ConnectivityManager connMgr = (ConnectivityManager)
            current.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }
//    private static class DownloadWebPage extends AsyncTask<String, Void, String> {
//        TextView display;
//
//        public DownloadWebPage() {
//            super();
//        }
//
//        public DownloadWebPage(TextView display){
//            this.display = display;
//        }
//
//        @Override
//        protected String doInBackground(String... urls) {
//            try {
//                return downloadUrl(urls[0]);
//            } catch (IOException e){
//                return "URL invalid";
//            }
//        }

//        @Override
//        protected void onPostExecute(String result) {
//            display.setText(result);
//        }
//    }
//    public static void getWebPage(TextView display) {
//        new DownloadWebPage(display).execute("http://www.google.com");
//    }
}
