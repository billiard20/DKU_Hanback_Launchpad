package jniActivity;

import android.util.Log;

/**
 * Created by 강남호 on 2017-06-18.
 */

public class FullColor {
    static{
        System.loadLibrary("FullColor");
    }
    public native boolean Open();
    public native int Control(int led_num, int val1, int val2, int val3);
    public native boolean Close();

    public FullColor(){
        boolean ret = Open();
        if (!ret)
            Log.d("FullcolorLED", "Open fail");
    }

    void display(int n, int red, int green, int blue){
        Control(n, red, green, blue);
    }


}
