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

public class MainPageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void jumpToCamera(View view) {
        String content = get_text_from_editText();

        if (!isValid(content)) {
            return;
        }

        Intent intent = new Intent(this, CaptureActivity.class);
        intent.putExtra("roomNumber",content);
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
