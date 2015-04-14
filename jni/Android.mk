LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_C_INCLUDES := $(LOCAL_PATH)/LOCAL_C_INCLUDES /packetizer.h
LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib -llog  
#LOCAL_ALLOW_UNDEFINED_SYMBOLS := true
LOCAL_CPPFLAGS += -fexceptions

LOCAL_MODULE    := packetizerUtils2
LOCAL_SRC_FILES := packetizerUtils2.c \
Operate.c \
packetizer.c

include $(BUILD_SHARED_LIBRARY)


