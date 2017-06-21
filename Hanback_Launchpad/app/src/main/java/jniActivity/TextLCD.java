package jniActivity;

import android.util.Log;

/**
 * Created by 강남호 on 2017-06-19.
 */

public class TextLCD{

    static{
        System.loadLibrary("TextLCD");

    }

    public native boolean open();
    public native int control(String str, String str2);
    public native int clear();
    public native boolean close();
    public native int IOCtlDisplay (boolean data);
    public native int IOCtlReturnHome ();
    public native int IOCtlCursor(boolean data);
    public native int IOCtlBlink(boolean data);


    public TextLCD(){
        boolean ret = open();
        if (!ret)
            Log.d("TextLCD", "Open fail");
        clear();
        IOCtlReturnHome();
        IOCtlDisplay(true);
        IOCtlCursor(false);
        IOCtlBlink(false);
    }

    void UpdateValue(String str, String str2){
        clear();
        control(str, str2);
    }



}
