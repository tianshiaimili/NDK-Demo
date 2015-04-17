#include <string.h>
#include <android/log.h>
#include <jni.h>
#include <string.h>
#include "Operate.h"
#include "packetizer.h"
#define TAG    "LogUtils" // ������Զ����LOG�ı�ʶ
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__) // ����LOGD����
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
#define LOGW(...)  __android_log_print(ANDROID_LOG_WARN,TAG,__VA_ARGS__)
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)

#define NELEM(x) ((int) (sizeof(x) / sizeof((x)[0])))

static const char *classPathName = "com/example/PacketizerUtils";


////�и�
JNIEXPORT void JNICALL  native_splitToStripsWithMD5Min(JNIEnv* env, jobject thiz,
		jstring srcFile,jstring strip0,jstring strip1,jstring strip2) {

	  jthrowable exc;

//	 splitToStripsWithMD5Min(jstringTostring(env,srcFile), jstringTostring(env,strip0), jstringTostring(env,strip1), jstringTostring(env,strip2));
	   char * path0 = (char*)(*env)->GetStringUTFChars(env,srcFile,0);
	   char * path1 = (char*)(*env)->GetStringUTFChars(env,strip0,0);
	   char * path2 = (char*)(*env)->GetStringUTFChars(env,strip1,0);
	   char * path3 = (char*)(*env)->GetStringUTFChars(env,strip2,0);
	   LOGI("path0---- %s!\n", path0);
	 splitToStripsWithMD5Min(path0, path1, path2, path3);

	 exc = (*env)->ExceptionOccurred(env);
	 LOGE("**************");
     if (exc) {
    	 LOGE("error**************");
         jclass newExcCls;

         (*env)->ExceptionDescribe(env);

         (*env)->ExceptionClear(env);

         newExcCls = (*env)->FindClass(env,

                       "java/lang/IllegalArgumentException");

         if (newExcCls == NULL) {

             /* Unable to find the exception class, give up. */

             return;

         }

         (*env)->ThrowNew(env, newExcCls, "thrown from C code");

     }

}

JNIEXPORT void JNICALL  native_splitToStripsWithMD5(JNIEnv* env, jobject thiz,
		jstring srcFile,jstring strip0,jstring strip1,jstring strip2,jstring strip3) {

	  jthrowable exc;

//	 splitToStripsWithMD5Min(jstringTostring(env,srcFile), jstringTostring(env,strip0), jstringTostring(env,strip1), jstringTostring(env,strip2));
	   char * path0 = (char*)(*env)->GetStringUTFChars(env,srcFile,0);
	   char * path1 = (char*)(*env)->GetStringUTFChars(env,strip0,0);
	   char * path2 = (char*)(*env)->GetStringUTFChars(env,strip1,0);
	   char * path3 = (char*)(*env)->GetStringUTFChars(env,strip2,0);
	   char * path4 = (char*)(*env)->GetStringUTFChars(env,strip3,0);
	   LOGI("path0---- %s!\n", path0);
	   splitToStripsWithMD5(path0, path1, path2, path3,path4);

	 exc = (*env)->ExceptionOccurred(env);
	 LOGE("**************");
     if (exc) {
    	 LOGE("error**************");
         jclass newExcCls;

         (*env)->ExceptionDescribe(env);

         (*env)->ExceptionClear(env);

         newExcCls = (*env)->FindClass(env,

                       "java/lang/IllegalArgumentException");

         if (newExcCls == NULL) {

             /* Unable to find the exception class, give up. */

             return;

         }

         (*env)->ThrowNew(env, newExcCls, "thrown from C code");

     }

}




/////��ԭ
jint native_mergeStripsWithMD5(JNIEnv* env, jobject thiz,
		jstring strip0,jstring strip1,jstring strip2,jstring srcFile) {

	   char * path0 = (char*)(*env)->GetStringUTFChars(env,srcFile,0);
	   char * path1 = (char*)(*env)->GetStringUTFChars(env,strip0,0);
	   char * path2 = (char*)(*env)->GetStringUTFChars(env,strip1,0);
	   char * path3 = (char*)(*env)->GetStringUTFChars(env,strip2,0);
	   LOGI("path0---- %s!\n", path0);

	return mergeStripsWithMD5(path1, path2, path3,path0);

//	return mergeStripsWithMD5(jstringTostring(env,strip0), jstringTostring(env,strip1), jstringTostring(env,strip2),jstringTostring(env,srcFile));
}


JNIEXPORT void JNICALL doit(JNIEnv *env, jobject obj)

 {

     jthrowable exc;

     jclass cls = (*env)->GetObjectClass(env, obj);

     jmethodID mid = (*env)->GetMethodID(env, cls, "callback", "()V");

     if (mid == NULL) {
    	 LOGE("error**************");
//         return;

     }
	 LOGE("error****CallVoidMethod------------");
//     (*env)->CallVoidMethod(env, obj, mid);
     LOGE("error****ExceptionOccurred------------");
     exc = (*env)->ExceptionOccurred(env);

     if (exc) {

         /* We don't do much with the exception, except that

            we print a debug message for it, clear it, and

            throw a new exception. */

         jclass newExcCls;

         (*env)->ExceptionDescribe(env);

         (*env)->ExceptionClear(env);

         newExcCls = (*env)->FindClass(env,

                       "java/lang/IllegalArgumentException");

         if (newExcCls == NULL) {

             /* Unable to find the exception class, give up. */

             return;

         }

         (*env)->ThrowNew(env, newExcCls, "thrown from C code");

     }

 }


//static JNINativeMethod methods[] = {
//        {"splitToStripsWithMD5Min", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I", (void*)native_splitToStripsWithMD5Min},
//        {"mergeStripsWithMD5", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I", (void*)native_mergeStripsWithMD5},
//        {"doit", "()V", (void*)doit},
//};

static JNINativeMethod methods[] = {
        {"splitToStripsWithMD5Min", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", (void*)native_splitToStripsWithMD5Min},
        {"mergeStripsWithMD5", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I", (void*)native_mergeStripsWithMD5},
        {"doit", "()V", (void*)doit},
        {"splitToStripsWithMD5", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", (void*)native_splitToStripsWithMD5}
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
