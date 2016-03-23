package test.ad.jz.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import test.ad.jz.entity.Action;
import test.ad.jz.entity.Request;
import test.ad.jz.log.L;

public class Http {
	private static String postInfo(String param, String method) {
		URL url;
		String result = null;
		try {
			url = new URL("http://bz.ooxxz.com/" + method);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Content-Type", "text/plain");
			conn.setRequestProperty("User-Agent", "Apache-HttpClient/UNAVAILABLE (java 1.4)");

			conn.getOutputStream().write(param.getBytes());
			conn.getOutputStream().flush();

			InputStream is = conn.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = 0;
			byte buffer[] = new byte[1024];
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
				baos.flush();
			}
			result = baos.toString();
			baos.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String post(Request r){
		L.log("post:"+r.toString());
		return postInfo(new String(Base64.encode(r.toString().getBytes(), 0)), "a");
	}
	public static String post(Action a){
		L.log("post:"+a.toString());
		return postInfo(new String(Base64.encode(a.toString().getBytes(), 0)), "b");
	}
}
