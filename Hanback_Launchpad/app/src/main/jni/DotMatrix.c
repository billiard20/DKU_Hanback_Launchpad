#include "jniActivity_DotMatrix.h"
//
// Created by 강남호 on 2017-06-16.
//

int fd = -1;

JNIEXPORT jboolean JNICALL Java_jniActivity_DotMatrix_Open
        (JNIEnv *env, jobject obj){
    fd = open("/dev/fpga_dotmatrix", O_RDWR | O_SYNC);
    if (fd < 0) return -errno;
    else return 1;
}

JNIEXPORT jint JNICALL Java_jniActivity_DotMatrix_Control
        (JNIEnv *env, jobject obj, jstring data) {
    if (fd < 0) fd = open("/dev/fpga_dotmatrix", O_RDWR | O_SYNC);
    if (fd < 0) return -errno;
    const char *buf;
    int ret, len;
    char str[100];

    buf = (*env)->GetStringUTFChars(env, data, 0);
    len = (*env)->GetStringLength(env, data);

    ret = write(fd, buf, len);

    return 1;

}

JNIEXPORT jboolean JNICALL Java_jniActivity_DotMatrix_Close
        (JNIEnv *env, jobject obj) {

    if (fd < 0) return -1;
    close(fd);
    fd = -1;
    return 1;
}

