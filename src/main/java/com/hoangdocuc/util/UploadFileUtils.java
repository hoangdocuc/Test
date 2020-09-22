package com.hoangdocuc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class UploadFileUtils {

	public void writeOrUpdate(byte[] bytes, String path) {
		// kiem tra folder ton tai hay chua, tao folder
		// ghi file
		String root = System.getProperty("catalina.home");
		File dir = null;
		File dir2 = null;
		File dir3 = null;
		boolean bool = false;
		try {
			// returns pathnames for files and directory
			dir = new File(root + File.separator + "assets");
			// create
			if(!dir.exists()) {
				bool = dir.mkdir();
			}
			dir2 = new File(dir + File.separator + "user");
			if(!dir2.exists()) {
				bool = dir2.mkdir();
			}
			dir3 = new File(dir2 + File.separator + "img");
			if(!dir3.exists()) {
				bool = dir3.mkdir();
			}
			// print
			System.out.print("Directory created? " + bool);
		} catch (Exception e) {
			// if any error occurs
			e.printStackTrace();
		}
		// kiem tra file ton tai hay chua, tao file
		// ghi file
		File file = new File(dir3.getAbsoluteFile() + File.separator + path);
		try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
			if (!file.exists()) {
				file.createNewFile();
			}
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
			fileOutputStream.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
