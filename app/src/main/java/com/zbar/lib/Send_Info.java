package com.zbar.lib;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.zbar.lib.R;

public class Send_Info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String qr_code = intent.getStringExtra("qrCode");
        int room_number = intent.getIntExtra("roomNumber", -1);

        setContentView(R.layout.activity_send_info);

        TextView roomNumberView = (TextView) findViewById(R.id.roomNumberResult);
        TextView qrCodeView = (TextView) findViewById(R.id.QRCodeResult);
        // TODO: Get Instruction from server
        roomNumberView.append(Integer.toString(room_number));
        if (qr_code != null)
            qrCodeView.append(qr_code);
    }

}
