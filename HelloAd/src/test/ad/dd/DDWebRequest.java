package test.ad.dd;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.util.Log;
import cs.network.configs.Config;
import cs.network.request.ClientSessionManager;
import cs.utils.io.StreamUtils;

public class DDWebRequest {
	/**
	 * 模拟dd post请求。
	 * 
	 * @param context
	 * @param url
	 *            请求地址,如：http://115.28.142.241:8080/adCenter/app/get
	 * @param userAgent
	 *            格式如 :UserAgent : Dalvik/1.6.0 (Linux; U; Android 4.4.2; NEXO
	 *            Smarty Build/KOT49H)
	 * @param params
	 *            请求参数，格式如clientUUID=507835236185671&time=1458725226152 ...
	 */
	public static String request(Context context, String url, String userAgent,
			String params) {
		Log.e("lkt", "request start:" + url + " agent:" + userAgent
				+ " params:" + params);
		try {
			String str4 = ClientSessionManager.getSession(context);
			HttpURLConnection conn = ((HttpURLConnection) new URL(url)
					.openConnection());
			String str5 = userAgent + "hh" + Config.getApiver();
			if (str4 != null && !"".equals(str4)) {
				Log.e("lkt", "request with cookie:" + str4);
				conn.setRequestProperty("Cookie", str4);
			}
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

			String str3 = conn.getHeaderField("Set-Cookie");
			if (str3 != null) {
				ClientSessionManager.setSession(
						str3.substring(0, str3.indexOf(";")), context);
			}

			InputStream localObject1 = conn.getInputStream();
			String str1 = StreamUtils.inputToString(localObject1);
			Log.e("lkt", "request result : " + str1);
			return str1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
