LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_C_INCLUDES := $(LOCAL_PATH)/LOCAL_C_INCLUDES 
LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib -llog  

LOCAL_MODULE    := packetizerUtils2
LOCAL_SRC_FILES := \
Operate.c \
packetizerUtils2.c \
packetizer.c

include $(BUILD_SHARED_LIBRARY)


