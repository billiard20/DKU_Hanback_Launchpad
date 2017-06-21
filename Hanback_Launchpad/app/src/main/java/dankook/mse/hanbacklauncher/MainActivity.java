package dankook.mse.hanbacklauncher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import jniActivity.jniCombine;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int bgmNum = 5;
    public static final int beatNum = 9;
    private int tflag = 0x0;
    MusicManager musicManager = new MusicManager(MainActivity.this, bgmNum);
    BeatManager beatManager = new BeatManager(MainActivity.this, beatNum);
    //timeThread tThread = new timeThread();
    jniCombine JNI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JNI = new jniCombine(getApplicationContext());
        Button btn_BGM1 = (Button) findViewById(R.id.bgm_button1);
        Button btn_BGM2 = (Button) findViewById(R.id.bgm_button2);
        Button btn_BGM3 = (Button) findViewById(R.id.bgm_button3);
        Button btn_BGM4 = (Button) findViewById(R.id.bgm_button4);
        Button btn_BGM5 = (Button) findViewById(R.id.bgm_button5);
        Button btn_STP = (Button) findViewById(R.id.bgm_stop);
        btn_BGM1.setOnClickListener(MainActivity.this);
        btn_BGM2.setOnClickListener(MainActivity.this);
        btn_BGM3.setOnClickListener(MainActivity.this);
        btn_BGM4.setOnClickListener(MainActivity.this);
        btn_BGM5.setOnClickListener(MainActivity.this);
        btn_STP.setOnClickListener(MainActivity.this);
        Mythread m_thread = new Mythread();
        LEDThread l_thread = new LEDThread();
        DotThread d_thread = new DotThread();
        l_thread.start();
        l_thread.setPriority(10);
        m_thread.start();
        d_thread.start();
        //JNI.displayDot(0);
        // m_thread.setPriority(10);
        /*tThread.setDaemon(true);
        tThread.start();*/

        // Example of a call to a native method
    }


    private int BGMcheck[] = new int[bgmNum];

    int LED = 0;
    int m_index = -1;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bgm_button1:
                m_index = 0;
                if (BGMcheck[m_index] != 1) {
                    tflag |= 0b10;
                    LED |= 0x01;
                    musicManager.play(R.raw.bgm_dark_hiphop, m_index);
                    BGMcheck[m_index] = 1;
                }else{
                    LED &= ~(0x01);
                    tflag &= 0b01;
                    musicManager.stop(m_index);
                    BGMcheck[m_index] = 0;
                }
                break;
            case R.id.bgm_button2:
                m_index = 1;
                if (BGMcheck[m_index] != 1) {
                    tflag |= 0b10;
                    LED |= 0x02;
                    musicManager.play(R.raw.bgm_dreamy, m_index);
                    BGMcheck[m_index] = 1;
                }else{
                    LED &= ~(0x02);
                    tflag &= 0b01;
                    musicManager.stop(m_index);
                    BGMcheck[m_index] = 0;
                }
                break;
            case R.id.bgm_button3:
                m_index = 2;
                if (BGMcheck[m_index] != 1) {
                    tflag |= 0b10;
                    LED |= 0x04;
                    musicManager.play(R.raw.bgm_electro, m_index);
                    BGMcheck[m_index] = 1;
                }else{
                    LED &= ~(0x04);
                    tflag &= 0b01;
                    musicManager.stop(m_index);
                    BGMcheck[m_index] = 0;
                }
                break;
            case R.id.bgm_button4:
                m_index = 3;
                if (BGMcheck[m_index] != 1) {
                    tflag |= 0b10;
                    LED |= 0x08;
                    musicManager.play(R.raw.bgm_hiphop_lead, m_index);
                    BGMcheck[m_index] = 1;
                }else{
                    LED &= ~(0x08);
                    tflag &= 0b01;
                    musicManager.stop(m_index);
                    BGMcheck[m_index] = 0;
                }
                break;
            case R.id.bgm_button5:
                m_index = 4;
                if (BGMcheck[m_index] != 1) {
                    tflag |= 0b10;
                    LED |= 0x10;
                    musicManager.play(R.raw.bgm_trap, m_index);
                    BGMcheck[m_index] = 1;
                }else{
                    LED &= ~(0x10);
                    tflag &= 0b01;
                    musicManager.stop(m_index);
                    BGMcheck[m_index] = 0;
                }
                break;
            case R.id.bgm_stop:
                tflag &= 0b01;
                LED &= 0xE0;
                stopBGM();
                break;
        }

        JNI.TurnLEDon(LED);
    }

    private void stopBGM(){
        for (int j = 0; j < bgmNum; j++){
            if (BGMcheck[j] == 1) {
                musicManager.stop(j);
                BGMcheck[j] = 0;
            }
        }
    }
    private void stopBeat(){
        for (int j = 0; j < beatNum; j++){
            if (Beatcheck[j] == 1) {
                beatManager.stop(j);
                Beatcheck[j] = 0;
            }
        }
    }

    private int Beatcheck[] = new int[beatNum];

    int b_index = -1;
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){
        switch(keycode){
            case KeyEvent.KEYCODE_9:
                textchange = true;
                b_index = 8;
                if (Beatcheck[b_index] != 1) {
                    tflag |= 0b01;
                    beatManager.play(R.raw.beat_vocal, b_index);
                    Beatcheck[b_index] = 1;
                }else{
                    tflag &= 0b10;
                    beatManager.stop(b_index);
                    Beatcheck[b_index] = 0;
                }
                break;
            case KeyEvent.KEYCODE_8:
                textchange = true;
                b_index = 7;
                if (Beatcheck[b_index] != 1) {
                    tflag |= 0b01;
                    beatManager.play(R.raw.beat_trip, b_index);
                    Beatcheck[b_index] = 1;
                }else{
                    tflag &= 0b10;
                    beatManager.stop(b_index);
                    Beatcheck[b_index] = 0;
                }
                break;
            case KeyEvent.KEYCODE_7:
                textchange = true;
                b_index = 6;
                if (Beatcheck[b_index] != 1) {
                    tflag |= 0b01;
                    beatManager.play(R.raw.beat_funky, b_index);
                    Beatcheck[b_index] = 1;
                }else{
                    tflag &= 0b10;
                    beatManager.stop(b_index);
                    Beatcheck[b_index] = 0;
                }
                break;
            case KeyEvent.KEYCODE_6:
                textchange = true;
                b_index = 5;
                if (Beatcheck[b_index] != 1) {
                    tflag |= 0b01;
                    beatManager.play(R.raw.beat_talking, b_index);
                    Beatcheck[b_index] = 1;
                }else{
                    tflag &= 0b10;
                    beatManager.stop(b_index);
                    Beatcheck[b_index] = 0;
                }
                break;
            case KeyEvent.KEYCODE_5:
                textchange = true;
                b_index = 4;
                if (Beatcheck[b_index] != 1) {
                    tflag |= 0b01;
                    beatManager.play(R.raw.beat_break, b_index);
                    Beatcheck[b_index] = 1;
                }else{
                    tflag &= 0b10;
                    beatManager.stop(b_index);
                    Beatcheck[b_index] = 0;
                }
                break;
            case KeyEvent.KEYCODE_4:
                textchange = true;
                b_index = 3;
                if (Beatcheck[b_index] != 1) {
                    tflag |= 0b01;
                    beatManager.play(R.raw.beat_disco, b_index);
                    Beatcheck[b_index] = 1;
                }else{
                    tflag &= 0b10;
                    beatManager.stop(b_index);
                    Beatcheck[b_index] = 0;
                }
                break;
            case KeyEvent.KEYCODE_3:
                textchange = true;
                b_index = 2;
                if (Beatcheck[b_index] != 1) {
                    tflag |= 0b01;
                    beatManager.play(R.raw.beat_machine, b_index);
                    Beatcheck[b_index] = 1;
                }else{
                    tflag &= 0b10;
                    beatManager.stop(b_index);
                    Beatcheck[b_index] = 0;
                }
                break;
            case KeyEvent.KEYCODE_2:
                textchange = true;
                b_index = 1;
                if (Beatcheck[b_index] != 1) {
                    tflag |= 0b01;
                    beatManager.play(R.raw.beat_drum_classic, b_index);
                    Beatcheck[b_index] = 1;
                }else{
                    tflag &= 0b10;
                    beatManager.stop(b_index);
                    Beatcheck[b_index] = 0;
                }
                break;
            case KeyEvent.KEYCODE_1:
                textchange = true;
                b_index = 0;
                if (Beatcheck[b_index] != 1) {
                    tflag |= 0b01;
                    beatManager.play(R.raw.beat_drum_125, b_index);
                    Beatcheck[b_index] = 1;
                }else{
                    tflag &= 0b10;
                    beatManager.stop(b_index);
                    Beatcheck[b_index] = 0;
                }
                break;
            case KeyEvent.KEYCODE_0:
                tflag &= 0b10;
                stopBeat();
                break;
            case KeyEvent.KEYCODE_BACK:
                return super.onKeyDown(keycode, event);
        }

        return false;
    }

    private boolean musicOn(){
        for (int i = 0; i < bgmNum; i++){
            if (BGMcheck[i] == 1)
                return true;
        }
        for (int i = 0; i < beatNum; i++){
            if (Beatcheck[i] == 1)
                return true;
        }
        return false;
    }


    /*private class timeThread extends Thread{
        public void run(){
            while(true){
                Log.i("time","time");
                if(tflag == 0)
                    break;
                Log.i("time","not b");
                JNI.timestart();
                long r = (System.currentTimeMillis());
                for (int i = 0; i < 20; i++)
                    JNI.tick((int)r/1000);
            }
        }
    }*/


    int preBGM = 0;
    int preBeat = 0;
    int preflag = 0;

    private class Mythread extends Thread{
        public void run(){
            try{
                while(true){
                    int BGMvol = JNI.VolBGM();
                    int Beatvol = JNI.VolBeat();
                    if (BGMvol != preBGM)
                        musicManager.changeVolume(BGMvol, BGMcheck, bgmNum);
                    if (Beatvol != preBeat)
                        beatManager.changeVolume(Beatvol,Beatcheck, beatNum);
                    preBeat = Beatvol;
                    preBGM = BGMvol;
                    Thread.sleep(500);
                    /*if(tflag > 0) {
                        Log.i("time", "not b");
                        JNI.timestart();
                        long r = (System.currentTimeMillis());
                        for (int i = 0; i < 20; i++)
                            JNI.tick((int) r / 1000);
                    }*/
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    boolean textchange = true;

    private class DotThread extends Thread{
        int dot = 1;
        public void run(){
            try{
                while(true){
                    if (tflag != preflag) {
                        if (tflag > 0){
                            dot = 2;
                            preflag = tflag;
                        }
                        if (tflag < 1){
                            dot = 1;
                            preflag = 0;
                        }
                    }
                    JNI.displayDot(dot);
                    Thread.sleep(500);
                    /*if (textchange) {
                        if ((tflag &= 0x01) == 0x01) {
                            String str = "";
                            for (int i = 0; i < beatNum; i++) {
                                if (Beatcheck[i] == 1)
                                    str = str + " " + Integer.toString(i+1);
                            }
                            Log.i("text", str);
                            JNI.displayTEXT("Beat Playing", str);
                            str = "";
                        } else {
                            JNI.displayTEXT("Hanback", "LaunchPad");
                        }
                        textchange = false;
                    }*/
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    int[] getRandomRGB(){
        int random[] = new int[3];
        random[0] = (int) (Math.random() * 154);
        random[1] = (int) (Math.random() * 154);
        random[2] = (int) (Math.random() * 154);
        return random;
    }

    private class LEDThread extends Thread{
        int init = 0;
        public void run(){
            try{
                while(true) {
                    if (musicOn()) {
                        for (int i = 6; i < 10; i++){
                            int color[] = getRandomRGB();
                            JNI.displayOLED(i, color[0], color[1], color[2]);
                        }
                        JNI.displayImage(2);
                        init = 1;
                    }else {
                        if (init == 1)
                            JNI.displayOLED(5, 0, 0, 0);
                        JNI.displayImage(1);
                        init = 0;
                    }
                    Thread.sleep(500);
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private long lastTimeBackPressed;
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()-lastTimeBackPressed<1500){
            finish();
            return;
        }

        Toast.makeText(this,"Press Back again to exit",Toast.LENGTH_SHORT).show();

        lastTimeBackPressed = System.currentTimeMillis();

    }

    public void onResume(){
        //JNI = new jniCombine(getApplicationContext());
        super.onResume();
    }

    public void turnoff(){
        stopBGM();
        stopBeat();
        JNI.turnoff();
    }

    @Override
    public void onPause(){
        turnoff();
        super.onPause();
    }

    @Override
    public void onStop(){
        turnoff();
        //JNI.exit();
        //JNI = null;
        super.onStop();
    }

}
