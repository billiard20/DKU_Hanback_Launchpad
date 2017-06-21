package jniActivity;

/**
 * Created by 강남호 on 2017-06-16.
 */

public class Segment implements jniInterface{

    static{
        System.loadLibrary("7Segment");

    }

    public native boolean SegmentControl(int data);
    public native boolean SegmentIOControl(int data);

    @Override
    public int UpdateValue() {
        return 0;
    }

    @Override
    public boolean UpdateValue(int data) {
        return false;
    }
}
