package com.zbar.lib;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.lang.Integer;

import com.zbar.lib.R;

public class Main_Page extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void jump_to_camera(View view) {
        String content = get_text_from_editText();

        if (!isValid(content)) {
            return;
        }
        String number_only = content.replaceAll("-","");
        int room_number = Integer.parseInt(number_only);

        IndoorNavigation app = (IndoorNavigation) this.getApplicationContext();
        app.setRoomNumber(room_number);


//        Intent start_broadcast_service = new Intent(this, Send_Info.class);
//        start_broadcast_service.putExtra("room_number", room_number);

        Intent intent = new Intent(this, CaptureActivity.class);
        startActivity(intent);
    }

    private boolean isValid(String content) {
        String number_only = content.replaceAll("-","");
        try {
            int number = Integer.parseInt(number_only);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private String get_text_from_editText() {
        EditText editText = (EditText) findViewById(R.id.room_number);
        return editText.getText().toString();
    }

}
