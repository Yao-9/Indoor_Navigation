package com.zbar.lib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.InputMismatchException;

public class HTTPUtil {


    public static void getWebPage(TextView display) {
        new DownloadWebPage(display).execute("http://www.google.com");
    }

    private static class DownloadWebPage extends AsyncTask<String, Void, String> {
        TextView display;

        public DownloadWebPage() {
            super();
        }

        public DownloadWebPage(TextView display){
            this.display = display;
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e){
                return "URL invalid";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            display.setText(result);
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

    private static String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        int len = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();

            int res = conn.getResponseCode();
            Log.d("DEBUG", "response is " + res);
            is = conn.getInputStream();

            String contengAsString = readIt(is, len);
            return contengAsString;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public static String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }
}
