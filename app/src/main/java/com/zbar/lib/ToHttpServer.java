package com.zbar.lib;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import java.util.ArrayList;

public class ToHttpServer extends Service {
    static final int MSG_REGISTER_CLIENT = 1;
    static final int MSG_UNREGISTER_CLIENT = 2;
    static final int MSG_SET_ROOM_NUMBER = 3;
    static final int MSG_SET_QR_CODE = 4;

    final Messenger mMessenger = new Messenger(new Receiver());

    private static boolean isRunning = false;
    ArrayList<Messenger> mClients = new ArrayList<Messenger>();

    String room_number;
    String qr_code;


    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    class Receiver extends Handler {
        @Override
        public void handleMessage (Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT:
                    mClients.add(msg.replyTo);
                    break;
                case MSG_UNREGISTER_CLIENT:
                    mClients.remove(msg.replyTo);
                    break;
                case MSG_SET_ROOM_NUMBER:
                    room_number = msg.getData().getString("str");
                    Log.i("Service", "Room Number is set");
                    break;
                case MSG_SET_QR_CODE:
                    qr_code = msg.getData().getString("str");
                    Log.i("Service", "QrCode is set");
                    break;
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyService", "Received start id " + startId + ": " + intent);
        return START_STICKY; // run until explicitly stopped.
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Service", "Service Stopped");
    }
}
