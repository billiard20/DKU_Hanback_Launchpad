/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <fcntl.h>
#include <errno.h>
#include <sys/mman.h>
#include <termios.h>
/* Header for class jniActivity_TextLCD */

#ifndef _Included_jniActivity_TextLCD
#define _Included_jniActivity_TextLCD
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     jniActivity_TextLCD
 * Method:    open
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_jniActivity_TextLCD_open
  (JNIEnv *, jobject);

/*
 * Class:     jniActivity_TextLCD
 * Method:    control
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_jniActivity_TextLCD_control
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     jniActivity_TextLCD
 * Method:    clear
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_jniActivity_TextLCD_clear
  (JNIEnv *, jobject);

/*
 * Class:     jniActivity_TextLCD
 * Method:    close
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_jniActivity_TextLCD_close
  (JNIEnv *, jobject);

/*
 * Class:     jniActivity_TextLCD
 * Method:    IOCtlDisplay
 * Signature: (Z)I
 */
JNIEXPORT jint JNICALL Java_jniActivity_TextLCD_IOCtlDisplay
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     jniActivity_TextLCD
 * Method:    IOCtlReturnHome
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_jniActivity_TextLCD_IOCtlReturnHome
  (JNIEnv *, jobject);

/*
 * Class:     jniActivity_TextLCD
 * Method:    IOCtlCursor
 * Signature: (Z)I
 */
JNIEXPORT jint JNICALL Java_jniActivity_TextLCD_IOCtlCursor
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     jniActivity_TextLCD
 * Method:    IOCtlBlink
 * Signature: (Z)I
 */
JNIEXPORT jint JNICALL Java_jniActivity_TextLCD_IOCtlBlink
  (JNIEnv *, jobject, jboolean);

#ifdef __cplusplus
}
#endif
#endif
