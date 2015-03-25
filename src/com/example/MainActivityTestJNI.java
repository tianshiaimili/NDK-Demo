package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;
import android.widget.TextView;

public class MainActivityTestJNI extends Activity {

	public final static String testPath_W = "/sdcard/TestLibary/seg01.ts";
	public final static String testPath_R = "/sdcard/TestLibary/seg01r.ts";
	public final static String strip0 = "/sdcard/TestLibary/seg01-0.tss";
	public final static String strip1 = "/sdcard/TestLibary/seg01-1.tss";
	public final static String strip2 = "/sdcard/TestLibary/seg01-2.tss";
	
	public final static String testPath_Head = "/sdcard/TestLibary/8089a0c2959276ac5dca5cb7ad8d2170/test";
	public final static String testPath_FOLDER = "/sdcard/TestLibary/8089a0c2959276ac5dca5cb7ad8d2170/cutFile/";
	public final static String testPath_Recovery = "/sdcard/TestLibary/recoveryFile/";
	
	public final static String[] testPath_Array = { testPath_Head + 0 + ".ts",
			testPath_Head + 1 + ".ts", testPath_Head + 2 + ".ts", testPath_Head + 3 + ".ts",
			testPath_Head + 4 + ".ts", testPath_Head + 5 + ".ts", };
	
	private SparseArray<String> fileNameArray;
	/**存放全部的分割后的tss文件路径*/
	private List<String> pathLists ;
	/**把每个ts分割后的路径 放入到一个小集合中，按照test0-1.tss,test0-2.tss,test0-3.tss,test0r.ts的顺序*/
	private List<SparseArray<String>> cutPathLists;
	
	Handler handler = new Handler(){
		
		public void handleMessage(android.os.Message msg) {
			
			int code = msg.what;
			
			if(code == 0){
				String recoveryName= null;
				List<SparseArray<String>> paths = (List<SparseArray<String>>) msg.obj;
				LogUtils.d("the paths.size = "+paths);
				for(SparseArray<String> temArray : paths){
					
					recoveryName = recoveryNameByStrip(temArray.get(0));
					
					if(recoveryName != null){
						LogUtils.e("+++++++++++++++++++");
						mergeStripsWithMD5(temArray.get(0),temArray.get(1),temArray.get(2),recoveryName);
						
					}
					
				}
				
			}
			
		};
		
	};
	
	public String recoveryNameByStrip(String stripName){
		
		String completePath = null;
		
		if(stripName != null){
			
			int lineIndex = stripName.lastIndexOf("/");
			int horizontalLineIndex = stripName.lastIndexOf("-");
			String name = stripName.substring(lineIndex+1,horizontalLineIndex)+".ts";
			String header = stripName.substring(0, lineIndex+1);
			completePath = testPath_Recovery+name;
			LogUtils.d("the completePath =="+completePath);
			return completePath;
		}
		
		return null;
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textView = new TextView(getApplicationContext());
		int addString = add(10, 2000);
		LogUtils.e("----" + addString);
		textView.setText(addString + "  || 456");

		// String addTeString = addTest(null, 200);
		// LogUtils.e("the addTestString = "+addTeString);

		String pluString = puls(1203366) + "";
		LogUtils.e("the pluString = " + pluString);
		LogUtils.i("***********************");
		/**
		 * test 分割
		 */
//		new Thread(new Runnable() {
//			String stripTempName= null;
//
//			@Override
//			public void run() {
//
//				for (String path : testPath_Array) {
////					LogUtils.i("***********************path --"+path);
////					LogUtils.i("***********************getFileName --"+getFileName(path));
//					stripTempName = getFileName(path);
//					String writeFile = splitToStripsWithMD5Min(path, testPath_FOLDER+stripTempName+"-1.tss",
//							testPath_FOLDER+stripTempName+"-2.tss", testPath_FOLDER+stripTempName+"-3.tss") + "";
//					LogUtils.e("the writeFile = " + writeFile);
//				}
//
//			}
//		}).start();

		/**
		 * test 复原
		 */
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// for(String path:testPath_Array){
		//
		// String writeFile =
		// mergeStripsWithMD5(strip0,strip1,strip2,testPath_R)+"";
		// LogUtils.e("the writeFile = "+writeFile);
		// }
		// }
		// }).start();

		
		
		setContentView(textView);

		fileNameArray = new SparseArray<String>();
		pathLists = new ArrayList<String>();
		cutPathLists = new ArrayList<SparseArray<String>>();
		
		///切割
//		cutFile();
		//复原
		recoveryFile(testPath_FOLDER);
		
		
		
	}

	public native int add(int x, int y);

	public native String addTest(String x, int y);

	//
	public native int puls(int x);

	/**
	 * @param srcFile
	 *            要切割的源文件路径
	 * @param strip0
	 *            切割分成三部分 的第一份
	 * */
	public native int splitToStripsWithMD5Min(String srcFile, String strip0,
			String strip1, String strip2);

	/**
	 * 复原切割的文件
	 * 
	 * @param strip0
	 *            切割分成三部分 的第一份
	 * @param srcFile
	 *            复原的新的读取文件
	 * 
	 */
	public native int mergeStripsWithMD5(String strip0, String strip1,
			String strip2, String srcFile);

	static {

		System.loadLibrary("TesC");

	}

	/**
	 * 根据源文件获取生成切割文件名
	 * @param tempName
	 * @return
	 */
	public String getFileName(String tempName){
		String name = null;
		if(tempName != null){
			int index = tempName.lastIndexOf("/");
			name = tempName.substring(index+1, tempName.length() - 3);
			LogUtils.d("name = "+name);
			return name;
		}
		return null;
	}
	
	/**
	 * 复原 文件
	 */
	public void recoveryFile(String fileFolder){
		String temFileName= null;
		String cutName = null;
		int index = 0;
		File file = new File(fileFolder);
		File[] files = file.listFiles();
		if(files !=null && files.length > 0){
			
			for(File tempFile : files){
				
				temFileName = tempFile.getPath();
//				fileNameArray.put(index, temFileName);
				pathLists.add(temFileName);
				index ++;
//				LogUtils.e("the temFileName = "+temFileName);
			}
			
		}else {
			return;
		}
		
//		fileNameArray.
		Collections.sort(pathLists);
		LogUtils.e("the pathLists.size = "+pathLists.size());
		
		SparseArray<String> tempArray =null;
		for(int k = 0;k< pathLists.size() ;k++){
			
		LogUtils.i("__________________"+k);
			
			if(k % 3 == 0){
				LogUtils.d("the k == "+k);
				tempArray = new SparseArray<String>();
			}
			
			tempArray.put(k%3, pathLists.get(k));
			
			if((k+1) % 3 == 0){
				LogUtils.d("the k is =="+k);
				cutPathLists.add(tempArray);
			}
		}
		if(cutPathLists.size() >0)
		LogUtils.e("the cutPathLists.size = "+cutPathLists.size());
		////
		Message message = new Message();
		message.obj = cutPathLists;
		message.what = 0;
//		LogUtils.e("the message .obj = "+message.obj);
		handler.sendMessage(message);
	}
	
	/**
	 * 切割文件
	 */
	public void cutFile (){
		
		new Thread(new Runnable() {
		String stripTempName= null;

		@Override
		public void run() {

			for (String path : testPath_Array) {
//				LogUtils.i("***********************path --"+path);
//				LogUtils.i("***********************getFileName --"+getFileName(path));
				stripTempName = getFileName(path);
				String writeFile = splitToStripsWithMD5Min(path, testPath_FOLDER+stripTempName+"-1.tss",
						testPath_FOLDER+stripTempName+"-2.tss", testPath_FOLDER+stripTempName+"-3.tss") + "";
				LogUtils.e("the writeFile = " + writeFile);
			}

		}
	}).start();
		
	}
	
}
