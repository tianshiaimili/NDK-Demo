#include <string.h>
#include <android/log.h>
#include <jni.h>
#include <string.h>
#include "Operate.h"
#include "packetizer.h"
#define TAG    "LogUtils" // ������Զ����LOG�ı�ʶ
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__) // ����LOGD����
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define LOGW(...)  __android_log_print(ANDROID_LOG_WARN,LOG_TAG,__VA_ARGS__)
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)

#define NELEM(x) ((int) (sizeof(x) / sizeof((x)[0])))

static const char *classPathName = "com/example/PacketizerUtils";


////�и�
jint native_splitToStripsWithMD5Min(JNIEnv* env, jobject thiz,
		jstring srcFile,jstring strip0,jstring strip1,jstring strip2) {
	return splitToStripsWithMD5Min(jstringTostring(env,srcFile), jstringTostring(env,strip0), jstringTostring(env,strip1), jstringTostring(env,strip2));
}

/////��ԭ
jint native_mergeStripsWithMD5(JNIEnv* env, jobject thiz,
		jstring strip0,jstring strip1,jstring strip2,jstring srcFile) {
	return mergeStripsWithMD5(jstringTostring(env,strip0), jstringTostring(env,strip1), jstringTostring(env,strip2),jstringTostring(env,srcFile));
}


static JNINativeMethod methods[] = {
        {"splitToStripsWithMD5Min", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I", (void*)native_splitToStripsWithMD5Min},
        {"mergeStripsWithMD5", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I", (void*)native_mergeStripsWithMD5},
};

jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
    JNIEnv* env = NULL;
    jclass clazz;
    //��ȡJNI��������
    if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_4) != JNI_OK) {
    	LOGD("ERROR: GetEnv failed\n");
        return JNI_ERR;
    }
    //ע�᱾�ط���.Load Ŀ����
    clazz = (*env)->FindClass(env,classPathName);
    if (clazz == NULL)
    {
    	LOGD("Native registration unable to find class '%s'", classPathName);
        return JNI_ERR;
    }
    //ע�᱾��native����
    if((*env)->RegisterNatives(env, clazz, methods, NELEM(methods)) < 0)
    {
    	LOGD("ERROR: MediaPlayer native registration failed\n");
           return JNI_ERR;
    }

    /* success -- return valid version number */
    return JNI_VERSION_1_4;
}
