#include "jniActivity_FullColor.h"

//
// Created by 강남호 on 2017-06-18.
//

#define FULL_LED1 9
#define FULL_LED2 8
#define FULL_LED3 7
#define FULL_LED4 6
#define ALL_LED 5

int fd = -1;

JNIEXPORT jboolean JNICALL Java_jniActivity_FullColor_Open
        (JNIEnv *env, jobject obj) {
    fd = open("/dev/fpga_fullcolorled", O_WRONLY);
    if (fd < 0) return -errno;
    else return 1;
}
/*
 * Class:     jniActivity_FullColor
 * Method:    Control
 * Signature: (IIII)I
 */
JNIEXPORT jint JNICALL Java_jniActivity_FullColor_Control
        (JNIEnv *env, jobject obj, jint led_num, jint val1, jint val2, jint val3){
    int ret;
    char buf[3];
    ret = (int) led_num;
    switch (ret){
        case FULL_LED1:
            ioctl(fd, FULL_LED1);
            break;
        case FULL_LED2:
            ioctl(fd, FULL_LED2);
            break;
        case FULL_LED3:
            ioctl(fd, FULL_LED3);
            break;
        case FULL_LED4:
            ioctl(fd, FULL_LED4);
            break;
        case ALL_LED:
            ioctl(fd, ALL_LED);
            break;
    }
    buf[0] = val1;
    buf[1] = val2;
    buf[2] = val3;

    if (fd < 0) fd = open("/dev/fpga_fullcolorled", O_WRONLY);
    if (fd < 0) return -1;
    write(fd, buf, 3);

    return ret;
}

/*
 * Class:     jniActivity_FullColor
 * Method:    Close
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_jniActivity_FullColor_Close
        (JNIEnv *env, jobject obj) {

    if (fd < 0) return -1;
    close(fd);
    fd = -1;
    return 1;
}
