#include "jniActivity_Segment.h"

//
// Created by 강남호 on 2017-06-16.
//


JNIEXPORT jboolean JNICALL Java_jniActivity_Segment_SegmentControl
        (JNIEnv *env, jobject obj, jint data){
    int dev, ret;
    dev = open("dev/fpga_segment", O_RDWR | O_SYNC);

    if (dev != -1){
        ret = write(dev, &data, 4);
        close(dev);
    }else{
        return -1;
    }
    return 0;
}

/*
 * Class:     jniActivity_Segment
 * Method:    SegmentIOControl
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_jniActivity_Segment_SegmentIOControl
        (JNIEnv *env, jobject obj, jint data){
    int dev, ret;
    dev = open("dev/fpga_segment", O_RDWR | O_SYNC);
    if (dev != -1){
        ret = ioctl(dev, data, NULL, NULL);
        close(dev);
    }else{
        return -1;
    }
    return 0;
}


