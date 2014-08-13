package com.zbar.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.zbar.lib.util.HTTPUtil;

public class Send extends Activity {
    private static final String Tag = "Send Activity";
    private static TextView roomNumberView;
    private static TextView qrCodeView;
    private boolean isNetAvail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_info);

        roomNumberView = (TextView) findViewById(R.id.roomNumberResult);
        qrCodeView = (TextView) findViewById(R.id.QRCodeResult);
    }

    @Override
    protected void onResume() {
        Intent intent = getIntent();
        String qr_code = intent.getStringExtra("qrCode");
        int room_number = intent.getIntExtra("roomNumber", -1);

        isNetAvail = HTTPUtil.CheckNetwork(this);
        if (!isNetAvail) {
            //Show Message that Network is not Available
            Log.i(Tag, "Network is not available");
            return;
        }

        HTTPUtil.getWebPage(roomNumberView);
        super.onResume();
    }

//        roomNumberView.append(Integer.toString(room_number));
//        if (qr_code != null)
//            qrCodeView.append(qr_code);
//        HttpManager.get();
        //TODO: Send qr_code and roomNumber to Server(Make httpRequest)
        // postData(result, qrCode)
        //TODO: Get instruction from Server
}