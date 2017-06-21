package jniActivity;

import android.util.Log;

/**
 * Created by 강남호 on 2017-06-14.
 */

class DipSW implements jniInterface{

    static{
        System.loadLibrary("dipSW");
    }
    public native boolean Open();
    public native boolean Close();
    public native int GetValue();

    DipSW(){
        boolean ret = Open();
        if (!ret)
            Log.d("Dipsw", "Open fail");
    }

    @Override
    public int UpdateValue(){
        int newValue = GetValue();
        if ((newValue & 0x1) == 0x1) {
            return 1;
        }
        if ((newValue & 0x2) == 0x2) {
            return 2;
        }
        if ((newValue & 0x4) == 0x4) {
            return 3;
        }
        if ((newValue & 0x8) == 0x8) {
            return 4;
        }
        if ((newValue & 0x10) == 0x10) {
            return 5;
        }
        if ((newValue & 0x20) == 0x20) {
            return 6;
        }
        if ((newValue & 0x40) == 0x40) {
            return 7;
        }
        if ((newValue & 0x80) == 0x80) {
            return 8;
        }
        return 9;
    }
    int UpdaterightValue(){
        int newValue = GetValue();
        if ((newValue & 0x100) == 0x100) {
            return 1;
        }
        if ((newValue & 0x200) == 0x200) {
            return 2;
        }
        if ((newValue & 0x400) == 0x400) {
            return 3;
        }
        if ((newValue & 0x800) == 0x800) {
            return 4;
        }
        if ((newValue & 0x1000) == 0x1000) {
            return 5;
        }
        if ((newValue & 0x2000) == 0x2000) {
            return 6;
        }
        if ((newValue & 0x4000) == 0x4000) {
            return 7;
        }
        if ((newValue & 0x8000) == 0x8000) {
            return 8;
        }
        return 9;
    }
    @Override
    public boolean UpdateValue(int data) {
        return false;
    }
}
