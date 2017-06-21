#include "jniActivity_Image.h"

//
// Created by 강남호 on 2017-06-18.
//

int fd = -1;

enum{
    OLED_INIT=0x10, MENU_PRINT, WIN_CLEAR, IMAGE_DRAW, RECT_DRAW, LINE_DRAW, CIRCLE_DRAW, TEXT_PRINT}IOCTL_CMD;

typedef struct{
    unsigned short sx;
    unsigned short sy;
    unsigned short ex;
    unsigned short ey;
}clearimage;

JNIEXPORT jboolean JNICALL Java_jniActivity_Image_open
        (JNIEnv *env, jobject obj){
    fd = open("/dev/fpga_oled", O_WRONLY | O_NDELAY);
    if (fd < 0) return -errno;
    else return 1;
}

/*
 * Class:     jniActivity_Image
 * Method:    Control
 * Signature: ([I)V
 */

int OLEDClear(){
    if (fd < 0) fd = open("/dev/fpga_oled", O_WRONLY | O_NDELAY);
    if (fd < 0) return -errno;
    clearimage ci;
    ci.sx = (short) 0;
    ci.sy = (short) 0;
    ci.ex = (short) 127;
    ci.ey = (short) 127;
    int ret = ioctl(fd, WIN_CLEAR, &ci, sizeof(ci));
    if (ret > 0) return ret;
    return -1;
}

int OLEDImage(int *data){
    int ret;
    unsigned short buf[128*128];
    unsigned short r = 0, g = 0, b = 0;
    int temp;
    if (fd < 0) fd = open("/dev/fpga_oled", O_WRONLY | O_NDELAY);
    if (fd < 0) return -errno;
    for (int i = 0; i < 128*128; i++)
        buf[i] = 0xffff;
    if (fd >= 0){
        for (int i = 0; i < 128*128; i++){
            temp = data[i];
            b = (unsigned short)((temp & 0x000000FF) >> 3);
            g = (unsigned short)((temp & 0x0000FF00) >> 5);
            r = (unsigned short)((temp & 0x00FF0000) >> 8);
            buf[i] = ((r&0xf800) | (g&0x07e0) | (b&0x001f));
        }
        ret = write (fd, buf, 128*128*2);
    }else return fd;
    if (ret > 0) return ret;
    return -1;


}

JNIEXPORT void JNICALL Java_jniActivity_Image_Control
        (JNIEnv *env, jobject obj, jintArray image){
    int *data;
    data = (int*) (*env)->GetIntArrayElements(env, image, 0);
    OLEDImage(data);
    (*env)->ReleaseIntArrayElements(env, image, data, 0);
}

JNIEXPORT void JNICALL Java_jniActivity_Image_clear
        (JNIEnv *env, jobject obj) {
    OLEDClear();
}
/*
 * Class:     jniActivity_Image
 * Method:    close
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_jniActivity_Image_close
        (JNIEnv *env, jobject obj) {

    if (fd < 0) return -1;
    close(fd);
    fd = -1;
    return 1;
}
