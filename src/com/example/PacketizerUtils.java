package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.util.SparseArray;

import com.tvb.smartdownload.utils.LogUtils;

public class PacketizerUtils {

	/**���ȫ���ķָ���tss�ļ�·��*/
	private List<String> pathLists ;
	
	/**��ÿ��ts�ָ���·�� ���뵽һ��С�����У�����test0-1.tss,test0-2.tss,test0-3.tss,test0r.ts��˳��*/
	private List<SparseArray<String>> cutPathLists;
	
	private static PacketizerUtils packetizerUtils = null;
	
	static {
		System.loadLibrary("packetizerUtils");
	}
	
	public PacketizerUtils (){
		
		pathLists = new ArrayList<String>();
		cutPathLists = new ArrayList<SparseArray<String>>();
		
	}
	
	
	public static PacketizerUtils instance(){
		
		if(packetizerUtils == null){
			
			packetizerUtils = new PacketizerUtils();
			
		}
		return packetizerUtils;
	}
	
	
	/**
	 * ��ԭ�и���ļ�
	 * 
	 * @param strip0
	 *            �и�ֳ������� �ĵ�һ��
	 * @param srcFile
	 *            ��ԭ���µĶ�ȡ�ļ�
	 * 
	 */
	public native static int mergeStripsWithMD5(String strip0, String strip1,
			String strip2, String srcFile);
	
	
	/**
	 * @param srcFile
	 *            Ҫ�и��Դ�ļ�·��
	 * @param strip0
	 *            �и�ֳ������� �ĵ�һ��
	 * */
	public native static int splitToStripsWithMD5Min(String srcFile, String strip0,
			String strip1, String strip2);
	
	
	/**
	 * get the recovery .ts file path
	 * @param stripName the Strip.tss file path
	 * @return
	 */
	public static String recoveryNameByStrip(String stripName){
		String completePath = null;
		if(stripName != null){
			
			int lineIndex = stripName.lastIndexOf("/");
			int horizontalLineIndex = stripName.lastIndexOf("-");
			String name = stripName.substring(lineIndex+1,horizontalLineIndex)+".ts";
			String header = stripName.substring(0, lineIndex+1);
			completePath = header+name;
			LogUtils.d("the completePath =="+completePath);
			return completePath;
		}
		
		return null;
		
	}
	
	/**
	 * ��ԭ �ļ�
	 * @param fileFolder the tss folder path
	 * 
	 */
	public void recoveryFile(String fileFolder){
		String temFileName= null;
		String cutName = null;
		File file = new File(fileFolder);
		if(!file.exists()){
			return;
		}
		File[] files = file.listFiles();
		if(files !=null && files.length > 0){
			
			for(File tempFile : files){
				
				temFileName = tempFile.getPath();
				if(temFileName.endsWith("ts")){
					continue;
				}
				if(temFileName.equals("m3u8")){
					continue;
				}
				pathLists.add(temFileName);
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

		///
		if(cutPathLists != null && cutPathLists.size() > 0 ){
			
			String recoveryName= null;
//			List<SparseArray<String>> paths = (List<SparseArray<String>>) msg.obj;
			LogUtils.d("the paths.size = "+cutPathLists.size());
			for(SparseArray<String> temArray : cutPathLists){
				
				recoveryName = recoveryNameByStrip(temArray.get(0));
				
				if(recoveryName != null){
					LogUtils.e("+++++++++++++++++++");
					packetizerUtils.mergeStripsWithMD5(temArray.get(0),temArray.get(1),temArray.get(2),recoveryName);
					
				}
				
			}
			
		}
		
	
		////deleted tss file
		LogUtils.e("deleted file .......");
//		deleteAllTssFile(fileFolder);
		
		
	}
	
	/**
	 * 
	 * @param path_Array the ts array
	 * @param filePath   the folder path where to save the tss file
	 */
	public static void cutFileToTss(String[] path_Array,String filePath){
		String stripTempName= null;
		File file = null;

		if(path_Array != null && path_Array.length > 0 && filePath != null){
			
			for (String path : path_Array) {
//				LogUtils.i("***********************path --"+path);
//				LogUtils.i("***********************getFileName --"+getFileName(path));
				stripTempName = getFileName(path);
				String writeFile = splitToStripsWithMD5Min(path, filePath+stripTempName+"-1.tss",
						filePath+stripTempName+"-2.tss", filePath+stripTempName+"-3.tss") + "";
				LogUtils.e("the writeFile = " + writeFile);
				
//				file = new File(path);
//				file.delete();
				
			}
			
		}
		
	}
	
	/**
	 * 
	 * @param path_Array the ts array
	 * @param filePath   the folder path where to save the tss file
	 */
	public void cutFileToTss(List<String> path_Array,String filePath){
		String stripTempName= null;
		File file = null;
		if(path_Array != null && path_Array.size() > 0 && filePath != null){
			
			for (String path : path_Array) {
//				LogUtils.i("***********************path --"+path);
//				LogUtils.i("***********************getFileName --"+getFileName(path));
				stripTempName = getFileName(path);
				String writeFile = splitToStripsWithMD5Min(path, filePath+stripTempName+"-1.tss",
						filePath+stripTempName+"-2.tss", filePath+stripTempName+"-3.tss") + "";
				LogUtils.e("the writeFile = " + writeFile);
				
				file = new File(path);
				file.delete();
				
			}
			
		}
		
	}
	
	
	/**
	 * 
	 * @param tsPath the ts path
	 * @param filePath   the folder path where to save the tss file
	 */
	public void cutFileToTss(String tsPath,String filePath){
		String stripTempName= null;
		if(tsPath != null  && filePath != null){
			
//				LogUtils.i("***********************path --"+path);
//				LogUtils.i("***********************getFileName --"+getFileName(path));
			try {
				stripTempName = getFileName(tsPath);
				
				String writeFile = splitToStripsWithMD5Min(tsPath, filePath+stripTempName+"-1.tss",
						filePath+stripTempName+"-2.tss", filePath+stripTempName+"-3.tss") + "";
				LogUtils.e("the writeFile = " + writeFile);
				
			} catch (Exception e) {
				
				LogUtils.e("error --"+e.getMessage());
				
			}	
				
//				File file = new File(tsPath);
//				file.delete();
				
		}
	}
	
	
	/**
	 * ����Դ�ļ���ȡ�����и��ļ���
	 * @param tempName
	 * @return
	 */
	public static String getFileName(String tempName){
		String name = null;
		if(tempName != null){
			int index = tempName.lastIndexOf("/");
			name = tempName.substring(index+1, tempName.length() - 3);
			LogUtils.d("name = "+name);
			return name;
		}
		return null;
	}
	
	/**deleted all the Strip tss file
	 * 
	 * @param fileFolder the Strip tss folder path where they are 
	 */
	public void deleteAllTssFile(String fileFolder){
		LogUtils.e("deleteAllTssFile...........");
		String temFileName= null;
		String cutName = null;
		File file = new File(fileFolder);
		if(!file.exists()){
			return;
		}
		File[] files = file.listFiles();
		if(files !=null && files.length > 0){
			
			for(File tempFile : files){
				temFileName = tempFile.getPath();
				if(!temFileName.endsWith("ts")){
					continue;
				}
				tempFile.delete();
			}
			
		}else {
			return;
		}
		
	}
	
	
}
