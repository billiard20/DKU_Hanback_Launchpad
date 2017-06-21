LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := testled
LOCAL_SRC_FILES := testndk.c
LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := dipSW
LOCAL_SRC_FILES := dipSW.c
LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := 7Segment
LOCAL_SRC_FILES := Segment.c
LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := DotMatrix
LOCAL_SRC_FILES := DotMatrix.c
LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := FullColor
LOCAL_SRC_FILES := FullColor.c
LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := Image
LOCAL_SRC_FILES := Image.c
LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)


include $(CLEAR_VARS)

LOCAL_MODULE    := TextLCD
LOCAL_SRC_FILES := TextLCD.c
LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)