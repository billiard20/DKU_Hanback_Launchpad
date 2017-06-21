package jniActivity;

import android.util.Log;

/**
 * Created by 강남호 on 2017-06-14.
 */

class LED implements jniInterface{

    static{
        System.loadLibrary("testled");

    }

    public native boolean Open();
    public native boolean TurnOn(int data);
    public native boolean TurnOffAll();
    public native boolean Close();

    LED(){
        boolean ret = Open();
        if (!ret)
            Log.d("LED", "Open fail");
    }

    @Override
    public int UpdateValue() {
        return 0;
    }

    public boolean UpdateValue(int data){
        return TurnOn(data);
    }
}
