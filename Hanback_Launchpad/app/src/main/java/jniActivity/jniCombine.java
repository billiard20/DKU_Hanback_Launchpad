package jniActivity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by 강남호 on 2017-06-14.
 */

public class jniCombine {
    public DipSW dipSW;
    private LED led;
    private DotMatrix dotMatrix;
    private FullColor fullColor;
    private Image image;
    //private Segment segment;
    //private TextLCD textLCD;


    public jniCombine(Context context){
        dipSW = new DipSW();
        led = new LED();
        dotMatrix = new DotMatrix();
        fullColor = new FullColor();
        image = new Image(context);
        //textLCD = new TextLCD();
        //segment = new Segment();

    }

    public int VolBGM(){
        return dipSW.UpdateValue();
    }
    public int VolBeat(){
        return dipSW.UpdaterightValue();
    }
    public void displayDot(int d){
        if (d == 0)
            return;
        else if (d == 1)
            dotMatrix.display("Ready");
        else
            dotMatrix.display("Now Playing");
    }
    public void displayOLED(int n, int a, int b, int c){
        fullColor.display(n, a, b, c);
    }
    public void turnoff(){
        dotMatrix.Control(" ");
        led.TurnOffAll();
        fullColor.display(5, 0, 0, 0);
        image.clear();
    }
    public void exit(){
        led.Close();
        dipSW.Close();
        dotMatrix.Close();
        image.close();
        fullColor.Close();
        //textLCD.close();
    }
    public void TurnLEDon(int data){
        led.TurnOn(data);
    }
    public void displayImage(int n){
        image.UpdateValue(n);
    }
    /*public void displayTEXT(String str, String str2){
        textLCD.UpdateValue(str, str2);
    }*/
    /*public void timestart(){
        segment.SegmentIOControl(1);
    }
    public void tick(int data){
        segment.SegmentControl(data);
    }*/

}
