#include <string.h>
#include <android/log.h>
#include <jni.h>
#include <string.h>
#include "packetizer.h"
#define TAG    "LogUtils" // 这个是自定义的LOG的标识
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__);
#define LOGW(...)  __android_log_print(ANDROID_LOG_WARN,TAG,__VA_ARGS__);
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__);

#define NELEM(x) ((int) (sizeof(x) / sizeof((x)[0])))

static const char *classPathName = "com/example/PacketizerUtils";


//
//extern "C"{
//JNIEXPORT jstring JNICALL Java_com_example_myjnitest_MainActivity_stringFromJNI(JNIEnv *env,
//        jobject thiz);
//
//
//jint native_mergeStripsWithMD5(JNIEnv* env, jobject thiz,
//		jstring strip0,jstring strip1,jstring strip2,jstring srcFile);
//
//
////jint splitToStripsWithMD5Min(const char* src, const char* strip0, const char* strip1, const char* strip2);
////
////jint mergeStripsWithMD5(const char* strip0, const char* strip1, const char* strip2, const char* file);
//
//};



////切割
JNIEXPORT void JNICALL  native_splitToStripsWithMD5Min(JNIEnv* env, jobject thiz,
		jstring srcFile,jstring strip0,jstring strip1,jstring strip2) {

	  jthrowable exc;
	   char * path0 = (char*)env->GetStringUTFChars(srcFile,0);
	   char * path1 = (char*)env->GetStringUTFChars(strip0,0);
	   char * path2 = (char*)env->GetStringUTFChars(strip1,0);
	   char * path3 = (char*)env->GetStringUTFChars(strip2,0);
	   LOGI("")
	   LOGI("path0---- %s!\n", path0);
	 splitToStripsWithMD5Min(path0, path1, path2, path3);

	 exc = env->ExceptionOccurred();
	 LOGE("**************");
     if (exc) {
    	 LOGE("error**************");
         jclass newExcCls;

         env->ExceptionDescribe();

         env->ExceptionClear();

         newExcCls = env->FindClass("java/lang/IllegalArgumentException");

         if (newExcCls == NULL) {

             /* Unable to find the exception class, give up. */

             return;

         }

         env->ThrowNew(newExcCls, "thrown from C code");

     }

}

/////复原
jint native_mergeStripsWithMD5(JNIEnv* env, jobject thiz,
		jstring strip0,jstring strip1,jstring strip2,jstring srcFile) {

	   char * path0 = (char*)env->GetStringUTFChars(srcFile,0);
	   char * path1 = (char*)env->GetStringUTFChars(strip0,0);
	   char * path2 = (char*)env->GetStringUTFChars(strip1,0);
	   char * path3 = (char*)env->GetStringUTFChars(strip2,0);
//	   LOGD("")
	   LOGI("path0---- %s!\n", path0);

	return mergeStripsWithMD5(path1, path2, path3,path0);
}




JNIEXPORT void JNICALL doit(JNIEnv *env, jobject obj)

 {

     jthrowable exc;

     jclass cls = env->GetObjectClass(obj);

     jmethodID mid = env->GetMethodID(cls, "callback", "()V");

     if (mid == NULL) {
    	 LOGE("error**************");
//         return;

     }
	 LOGE("error****CallVoidMethod------------");
//     (*env)->CallVoidMethod(env, obj, mid);
     LOGE("error****ExceptionOccurred------------");
     exc = env->ExceptionOccurred();

     if (exc) {

         /* We don't do much with the exception, except that

            we print a debug message for it, clear it, and

            throw a new exception. */

         jclass newExcCls;

         env->ExceptionDescribe();

         env->ExceptionClear();

         newExcCls = env->FindClass("java/lang/IllegalArgumentException");

         if (newExcCls == NULL) {

             /* Unable to find the exception class, give up. */

             return;

         }

         env->ThrowNew(newExcCls, "thrown from C code");

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
};

jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
    JNIEnv* env = NULL;
    jclass clazz;
    //获取JNI环境对象
    if (vm->GetEnv( (void**) &env, JNI_VERSION_1_4) != JNI_OK) {
//    	LOGD("ERROR: GetEnv failed\n");
        return JNI_ERR;
    }
    //注册本地方法.Load 目标类
    clazz = env->FindClass(classPathName);
    if (clazz == NULL)
    {
//    	LOGD("Native registration unable to find class '%s'", classPathName);
        return JNI_ERR;
    }
    //注册本地native方法
    if(env->RegisterNatives(clazz, methods, NELEM(methods)) < 0)
    {
//    	LOGD("ERROR: MediaPlayer native registration failed\n");
           return JNI_ERR;
    }

    /* success -- return valid version number */
    return JNI_VERSION_1_4;
}
