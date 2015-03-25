#include <string.h>
#include <android/log.h>
#include <jni.h>
#include <string.h>
#include "Operate.h"
#include "packetizer.h"
#define TAG    "LogUtils" // ������Զ����LOG�ı�ʶ
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__) // ����LOGD����

//extern "C"{
//
//	jint Java_com_example_MainActivity_add(JNIEnv* env, jobject thiz,
//			jint x,jint y);
//
//}

/**
 *  Java �� ������native add ������ʵ��
 *
 *  	jint x	����X
 *  	jint y	����Y
 */

jint Java_com_example_MainActivityTestJNI_add(JNIEnv* env, jobject thiz,
		jstring x, jint y) {

	//�÷���Ϊ��ӡ�ķ���
//	__android_log_print(ANDROID_LOG_INFO, "JNIMsg", "Get Param:  x=%d y=%d ", x,y);
	 LOGD("########## i = %d------------------");
//	return 100;
	return add(x, y);
}

jstring Java_com_example_MainActivityTestJNI_addTest(JNIEnv* env, jobject thiz,
		jint x, jint y) {

	//�÷���Ϊ��ӡ�ķ���
//	__android_log_print(ANDROID_LOG_INFO, "JNIMsg", "Get Param:  x=%d y=%d ", x,y);
//	return 100;
	return addTest(x);
}

jint Java_com_example_MainActivityTestJNI_puls(JNIEnv* env, jobject thiz,
		jstring x) {

	//�÷���Ϊ��ӡ�ķ���
//	__android_log_print(ANDROID_LOG_INFO, "JNIMsg", "Get Param:  x=%d y=%d ", x,y);
//	return 100;
	return puls(x);
}

////�и�
jint Java_com_example_MainActivityTestJNI_splitToStripsWithMD5Min(JNIEnv* env, jobject thiz,
		jstring srcFile,jstring strip0,jstring strip1,jstring strip2) {

//	__android_log_print(ANDROID_LOG_INFO, "JNIMsg", "Get Param:  x=%d y=%d ", x,y);
//	return 100;
	return splitToStripsWithMD5Min(jstringTostring(env,srcFile), jstringTostring(env,strip0), jstringTostring(env,strip1), jstringTostring(env,strip2));
}

/////��ԭ
jint Java_com_example_MainActivityTestJNI_mergeStripsWithMD5(JNIEnv* env, jobject thiz,
		jstring strip0,jstring strip1,jstring strip2,jstring srcFile) {

//	__android_log_print(ANDROID_LOG_INFO, "JNIMsg", "Get Param:  x=%d y=%d ", x,y);
//	return 100;
	return mergeStripsWithMD5(jstringTostring(env,strip0), jstringTostring(env,strip1), jstringTostring(env,strip2),jstringTostring(env,srcFile));
}


