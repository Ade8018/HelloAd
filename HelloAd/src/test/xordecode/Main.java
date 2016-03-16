package test.xordecode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
	public static void main(String[] args) {
//		try {
//			FileInputStream fis = new FileInputStream(
//					"E:\\lkt\\encode\\cl");
//			copy(fis, "E:\\lkt\\encode\\c");
//			fis.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String str = "\t";
		byte[] b = str.getBytes();
		for (int i = 0; i < b.length; i++) {
			b[i]^= 0x6a;
		}
		System.out.println(new String(b));
	}

	// E:\\lkt\\encode\\sdk_.dex
	public static void copy(InputStream is, String dest) {
		if (is == null || dest == null) {
			throw new NullPointerException();
		}
		makeDestFileDir(dest);
		streamCopy(is, new File(dest));
	}

	private static void makeDestFileDir(String dest) {
		File destDir = new File(dest.substring(0, dest.lastIndexOf("\\")));

		if (destDir.isDirectory()) {
			if (!destDir.exists()) {
				destDir.mkdirs();
			}
		} else {
			throw new RuntimeException("请输入正确的目的文件路径");
		}
	}

	private static void streamCopy(InputStream is, File dest) {
		FileOutputStream fos = null;
		byte[] buf = new byte[512];
		int len = -1;
		try {
			fos = new FileOutputStream(dest);
			while ((len = is.read(buf)) != -1) {
				for (int i = 0; i < len; i++) {
					buf[i] ^= 8;
				}
				fos.write(buf, 0, len);
				fos.flush();
			}
		} catch (Exception e) {
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
