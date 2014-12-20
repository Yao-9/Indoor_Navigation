package com.zbar.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.zbar.lib.util.HTTPUtil;
import org.json.JSONObject;
import java.util.HashMap;

public class SendActivity extends Activity {
    private static final String Tag = "Send Activity";
    private static TextView uploadStatus;
    private boolean isNetAvail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_info);

        uploadStatus = (TextView) findViewById(R.id.uploadStatus);
    }

    @Override
    protected void onResume() {
        Intent intent = getIntent();
        String qrCode = intent.getStringExtra("qrCode");
        String roomNumber = intent.getStringExtra("roomNumber");

        isNetAvail = HTTPUtil.CheckNetwork(this);
        if (!isNetAvail) {
            /* Log Message that Network is not Available */
            Log.i(Tag, "Network is not available");
            return;
        }

        JSONObject res = packAsJSON("roomNum", roomNumber, "qr", qrCode);

        HTTPUtil.postJSONToServer(res, "http://warm-headland-2583.herokuapp.com/");
        super.onResume();
    }

	/* Parse into two key-value pairs, return a JSON object consisting of this two pairs */
    public JSONObject packAsJSON(String key1, String value1, String key2, String value2) {
        HashMap<String, String> res = new HashMap<String, String>();
        res.put(key1, value1);
        res.put(key2, value2);
        return new JSONObject(res);
    }
}