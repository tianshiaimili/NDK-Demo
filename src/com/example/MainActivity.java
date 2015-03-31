package com.example;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final static String testPath_W = "/sdcard/TestLibary/seg01.ts";
	public final static String testPath_R = "/sdcard/TestLibary/seg01r.ts";
	public final static String strip0 = "/sdcard/TestLibary/seg01-0.tss";
	public final static String strip1 = "/sdcard/TestLibary/seg01-1.tss";
	public final static String strip2 = "/sdcard/TestLibary/seg01-2.tss";
	
//	public final static String testPath_Head = "/sdcard/TestLibary/8089a0c2959276ac5dca5cb7ad8d2170/test";
//	public final static String testPath_FOLDER = "/sdcard/TestLibary/8089a0c2959276ac5dca5cb7ad8d2170/cutFile/";
	
	public final static String testPath_Head = "/sdcard/TestLibary/64667c6e9c4adc08b332945286df6/fileSequence";
	public final static String testPath_FOLDER = "/sdcard/TestLibary/64667c6e9c4adc08b332945286df6/cutFile/";
	
	public final static String testPath_Recovery = "/sdcard/TestLibary/recoveryFile/";
	
	public final static String[] testPath_Array = { testPath_Head + 0 + ".ts",
			testPath_Head + 1 + ".ts", testPath_Head + 2 + ".ts", testPath_Head + 3 + ".ts",
			testPath_Head + 4 + ".ts", testPath_Head + 5 + ".ts", };
	
	Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			
			
//			new Runnable() {
//				
//				@Override
//				public void run() {
//					PacketizerUtils packetizerUtils = PacketizerUtils.instance();
//					packetizerUtils.cutFileToTss(testPath_Array[0], testPath_FOLDER);
//				}
//			}.run();;
			
			
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textView = new TextView(getApplicationContext());
//		int addString = add(10, 2000);
//		LogUtils.e("----" + addString);
		textView.setText("  || 456");
		textView.setBackgroundColor(Color.parseColor("#CCCCCC"));

		setContentView(textView);
		
		handler.postAtTime(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					PacketizerUtils packetizerUtils = PacketizerUtils.instance();
					LogUtils.d("the path =="+testPath_Array[4]);
					packetizerUtils.cutFileToTss(testPath_Array[3].trim(), testPath_FOLDER);
					
//					packetizerUtils.doit();
					
				} catch (Exception e) {

					LogUtils.e("err0r--"+e.getMessage());
				
				}
				
			}
		},2000);
		
		///ÇÐ¸î
		//¸´Ô­
	}
	
}
