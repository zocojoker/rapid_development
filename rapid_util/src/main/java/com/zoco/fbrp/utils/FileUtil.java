package com.zoco.fbrp.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 文件工具类
 * 
 */
public class FileUtil {

	private static final Log log = LogFactory.getLog(FileUtil.class);
	/**
	 * 文件重命名
	 * 
	 * @param folderPath
	 *            目录
	 * @param oldName
	 *            原文件名
	 * @param newName
	 *            新文件名
	 */
	public static void renameFile(String folderPath, String oldName, String newName) {
		File file = null;
		try {
			if (!folderPath.endsWith(File.separator)) {
				folderPath = folderPath + File.separator;
			}

			file = new File(folderPath + oldName);
			if (file.exists()) {
				boolean returnVal = file.renameTo(new File(folderPath + newName));
				if (!returnVal) {
//					log.error("重命名文件{}to{}失败", folderPath + oldName, folderPath + newName);
				}
			}
		} catch (Exception e) {
//			log.error("重命名文件{}to{}异常，错误信息为{}", folderPath + oldName, folderPath + newName, e.getMessage(), e);
		}
	}

	/**
	 * 往文件里面写内容
	 * 
	 * @param folderPath
	 *            目录
	 * @param fileName
	 *            文件名
	 * @param content
	 *            内容
	 */
	public static void writeContentToFile(String folderPath, String fileName, String content) {
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		if (!folderPath.endsWith(File.separator)) {
			folderPath = folderPath + File.separator;
		}
		try {
			File filePath = new File(folderPath);
			boolean fileExistFlag = true;
			// 如果目录不存在就建一个新目录
			if (!filePath.exists()) {
				fileExistFlag = filePath.mkdir();
			}

			if (fileExistFlag) {
				File file = new File(folderPath + fileName);
				// 如果文件不存在就建一个新文件
				if (!file.exists()) {
					fileExistFlag = file.createNewFile();
				}

				if (fileExistFlag) {
					fileWriter = new FileWriter(file, true);
					printWriter = new PrintWriter(fileWriter);
					printWriter.println(content);
					printWriter.close();
					fileWriter.close();
				} else {
//					log.error("新建文件{}失败", folderPath + fileName);
				}
			} else {
//				log.error("新建目录{}失败", folderPath);
			}
		} catch (Exception e) {
//			log.error("往文件{}里面写{}异常，错误信息为{}", folderPath + fileName, content, e.getMessage(), e);
		} finally {
			try {
				if (null != printWriter) {
					printWriter.close();
				}

				if (null != fileWriter) {
					fileWriter.close();
				}
			} catch (Exception e) {
//				log.error("往文件{}里面写{}时关闭文件流异常，错误信息为{}", folderPath + fileName, content, e.getMessage(), e);
			}
		}
	}

}
