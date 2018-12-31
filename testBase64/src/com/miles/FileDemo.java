package com.miles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileDemo {
	
	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream(new File("C:\\Users\\pc\\Desktop\\Holy Bible.docx"));
			byte[] buf = new byte[1024];
			FileOutputStream fos = new FileOutputStream(new File("demo/bible.txt"));
			int i=0;
			while((i=fis.read(buf))!=-1) {
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
