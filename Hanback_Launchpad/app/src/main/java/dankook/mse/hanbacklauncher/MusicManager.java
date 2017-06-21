package dankook.mse.hanbacklauncher;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

/**
 * Created by 강남호 on 2017-06-12.
 */

class MusicManager implements MediaManagerInterface {

    public static MediaPlayer mPlayer[];
    private Context context;
    private int mNum;

    MusicManager(Context ctx, int index) {
        context = ctx;
        mPlayer = new MediaPlayer[index];
        mNum = index;
    }

    @Override
    public void play(int musicID, int index) {
        mPlayer[index] = MediaPlayer.create(context, musicID);
        mPlayer[index].start();
        mPlayer[index].setLooping(true);
    }

    @Override
    public void stop(int index) {
        if (mPlayer[index] != null) {
            if (mPlayer[index].isPlaying())
                mPlayer[index].stop();
            mPlayer[index].reset();
            //mPlayer[index].release();
        }
    }

    @Override
    public void changeVolume(int a, int check[], int num) {
        float vol = (float) a *12/100;
        if (vol > 1.0)
            vol = (float) 1.0;
        Log.i("BGMvolume", "" + vol);
        for (int i = 0; i < num; i++){
            if(check[i] == 1){
                Log.i("bgmcheck", " " + i);
                mPlayer[i].setVolume(vol, vol);
            }

        }
    }

}
