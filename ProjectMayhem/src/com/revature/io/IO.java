package com.revature.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IO {
	private static final String OUT_FILE = "output.txt";
	private static final String IN_FILE = "input.txt";
	//write method
	public void writeOutputStreamContents(String contents) {
		OutputStream os = null;
		File file  = new File(OUT_FILE);
		try {
			os = new FileOutputStream(file, true);
			os.write(contents.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} 
	
	public String readInputStreamContents() {
		InputStream is = null;
		File file = new File(IN_FILE);
		StringBuilder s = new StringBuilder();
		try {
			is = new FileInputStream(file);
			int b = 0;
			while((b=is.read())!= -1) {
				char c=(char) b;
				s.append(c);
			}
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s.toString();
	}
}
