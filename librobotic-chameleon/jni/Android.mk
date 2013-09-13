LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := librobotic-chameleon
LOCAL_SRC_FILES := robotic-chameleon.cpp
LOCAL_CFLAGS := -Wall -Wextra -Werror
LOCAL_STATIC_LIBRARIES := libyuv_static
include $(BUILD_SHARED_LIBRARY)

include $(LOCAL_PATH)/../../third-party/libyuv/Android.mk

include $(ANDROID_MAVEN_PLUGIN_MAKEFILE)
