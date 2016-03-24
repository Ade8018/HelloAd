package test.ad.dd;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import cs.network.configs.Config;
import cs.network.request.ClientSessionManager;
import cs.utils.io.StreamUtils;

public class DDWebRequest {
	public static void request(Context context, String str2, String params) {
		try {
			String str4 = ClientSessionManager.getSession(context);
			HttpURLConnection conn = ((HttpURLConnection) new URL(str2)
					.openConnection());
			// TODO 修改UserAgent
			String str5 = System.getProperty("http.agent") + "hh"
					+ Config.getApiver();
			conn.setRequestProperty("User-Agent", str5);
			conn.setRequestProperty("S-V", Config.getApiver());
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("POST");
			OutputStream localOutputStream = conn.getOutputStream();
			BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(
					localOutputStream);
			localBufferedOutputStream.write(params.getBytes());
			localBufferedOutputStream.flush();
			localBufferedOutputStream.close();
			if (str4 != null) {
				boolean bool2 = str4.equals("");
				Object localObject1 = null;
				if (!bool2) {
					conn.setRequestProperty("Cookie", str4);
				}
			}
			InputStream localObject1 = conn.getInputStream();
			String str1 = StreamUtils.inputToString((InputStream) localObject1);
		} catch (Exception e) {
		}
	}
}
