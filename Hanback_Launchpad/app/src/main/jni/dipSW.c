#include "jniActivity_DipSW.h"

//
// Created by 강남호 on 2017-06-14.
//


static int fd = -1;


JNIEXPORT jboolean JNICALL Java_jniActivity_DipSW_Open
        (JNIEnv *env, jobject obj){
    fd = open("/dev/fpga_dipsw", O_RDONLY);
    if (fd < 0) return -errno;
    else return 1;
}

JNIEXPORT jboolean JNICALL Java_jniActivity_DipSW_Close
        (JNIEnv *env, jobject obj) {

    if (fd < 0) return -1;
    close(fd);
    fd = -1;
    return 1;
}

JNIEXPORT jint JNICALL Java_jniActivity_DipSW_GetValue
        (JNIEnv *env, jobject obj) {
    int ret;
    int data;
    if (fd < 0)
        fd = open("/dev/fpga_dipsw", O_RDONLY);
    if (fd < 0) return -errno;
    ret = read(fd, &data, 4);
    //if (ret == 4)
    data &= 0xffff;
        return data;

    return -1;
}



