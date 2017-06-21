package jniActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import dankook.mse.hanbacklauncher.R;

/**
 * Created by 강남호 on 2017-06-18.
 */

class Image implements jniInterface{
    static{
        System.loadLibrary("Image");
    }
    public native boolean open();
    public native void Control(int[] image);
    public native boolean close();
    public native void clear();

    private Context context;

    private int curimg = 0;
    private Bitmap bm;

    Image(Context ctx) {
        context = ctx;
        boolean ret = open();
        if (!ret)
            Log.d("OLED", "Open fail");
    }

    @Override
    public int UpdateValue() {
        return 0;
    }

    @Override
    public boolean UpdateValue(int n) {
        if (curimg == n)
            return false;
        java.io.InputStream is0 = context.getResources().openRawResource(R.raw.launchpad);
        java.io.InputStream is1 = context.getResources().openRawResource(R.raw.dropthebeat);

        switch (n) {
            case 1:
                bm = BitmapFactory.decodeStream(is0);
                break;
            case 2:
                bm = BitmapFactory.decodeStream(is1);
                break;
        }
        curimg = n;

        int width = bm.getWidth();
        int height = bm.getHeight();
        int[] pixels = new int[width * height];
        bm.getPixels(pixels, 0, width, 0, 0, width, height);
        Control(pixels);
        return true;
    }
}
