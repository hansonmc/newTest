package com.jlc.api.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class FileUtils {
	/**
	 * 读写一个文件                      每次读写一个字符
	 * @param fileReaderUrl  文件读取路径
	 * @param fileWriterUrl  文件写入路径
	 * @return 0：路径错误  1：读写成功
	 */
	public static int readerWriterFileByChar(String fileReaderUrl,String fileWriterUrl){
		if(StringUtil.isEmpty(fileReaderUrl)||StringUtils.isEmpty(fileWriterUrl)){
			return 0;
		}
		BufferedReader bReader = null;
		BufferedWriter bWriter = null;
		try {
			bReader  = new BufferedReader(new FileReader(fileReaderUrl));
			bWriter  = new BufferedWriter(new FileWriter(fileWriterUrl));
			String content;
			while ((content = bReader.readLine())!=null) {
				bWriter.write(content);
				bWriter.newLine();
				bWriter.flush();
			}
		} catch (IOException e) {
			System.out.println("文件读写异常");
		}finally{
			try {
				bReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
	/**
	 * 在指定的目录下创建文件
	 * @param directory 文件夹目录   可存在也可不存在
	 * @param fileName  文件名称   名称前缀的长度必须大于2
	 * @return 创建的文件
	 */
	public static File createFile(String directory,String fileName){
		if(StringUtil.isEmpty(directory)||StringUtil.isEmpty(fileName)){
			return null;
		}
		String prefix = fileName.substring(0,fileName.indexOf("."));
		String suffix = fileName.substring(fileName.indexOf("."),fileName.length());
		File dirFile = new File(directory);
		//如果目录不存在则创建此目录
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		File file = null;
		try {
			File.createTempFile(prefix, suffix, dirFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	} 
	/**
	 * 在已存在的目录下创建目录
	 * @param directory 需要创建目录的名称
	 * @return true 创建成功  false  创建失败
	 */
	public static boolean createFolder(String directory){
		return new File(directory).mkdirs();
	}
	/**
	 * 重新命名文件名称   当前时间戳
	 * @param file
	 * @return
	 */
	public static String renameFile(File file,String fileName){
		Long lon = new Date().getTime();
		String fileNamesub  = lon.toString();
		return fileName.replace(fileName.substring(0,fileName.indexOf(".")),fileNamesub);
	}
}
