package com.zbar.lib;
import android.app.Application;

/**
 * Created by yao on 8/6/14.
 */
public class IndoorNavigation extends Application {
    private int roomNumber;
    private String qrCode;

    public int getRoomNumber() { return roomNumber; }

    public String getQrCode() { return qrCode; }

    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
}
