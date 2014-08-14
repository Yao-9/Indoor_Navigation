package com.zbar.lib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HTTPUtil {

    static String TAG = "HTTPUtil";

    static JSONObject JSONUpload = null;
    static boolean isJSONUploadValid = false;

    public static void postJSONToServer(JSONObject json, String url) {
        JSONUpload = json;
        isJSONUploadValid = true;
        new DownloadWebPage().execute(url);
    }

    private static class DownloadWebPage extends AsyncTask<String, Void, Void> {

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

    private static void postURL(String str_url) throws IOException {
        if (!isJSONUploadValid){
            Log.i(TAG, "JSON is not ready before send POST, Abort.");
            return;
        }
        try {
            URL url = new URL(str_url);
            HttpURLConnection connection  = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("IndoorNavigaion", "version 0.0.0");

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(JSONUpload.toString());
            writer.close();
        } catch (MalformedURLException e){
            Log.i(TAG,"malformed URL");
        } catch (IOException e){
            Log.i(TAG, "IOException");
        }
    }

    public static boolean CheckNetwork(Context current) {
        ConnectivityManager connMgr = (ConnectivityManager)
            current.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        else {
            return false;
        }
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
