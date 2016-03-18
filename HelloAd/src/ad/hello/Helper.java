package ad.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import test.ad.util.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Helper {
	public static String GetNetIp() {
		URL infoUrl = null;
		InputStream inStream = null;
		try {
			infoUrl = new URL("http://1212.ip138.com/ic.asp");
			URLConnection connection = infoUrl.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			int responseCode = httpConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				inStream = httpConnection.getInputStream();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inStream, "utf-8"));
				StringBuilder strber = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null)
					strber.append(line + "\n");
				inStream.close();
				// 从反馈的结果中提取出IP地址
				int start = strber.indexOf("[");
				int end = strber.indexOf("]", start + 1);
				line = strber.substring(start + 1, end);
				return line;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void resetVpn(Context context) {
		disconnect(context);
		Utils.sleep(0, 1);
		Object profile = getVpnProfile();
		connect(context, profile);
	}

	public static Object getVpnProfile() {
		try {
			Class clsKeyStore = Class.forName("android.security.KeyStore");
			Method methodGetInstance = clsKeyStore.getDeclaredMethod(
					"getInstance", null);
			methodGetInstance.setAccessible(true);
			Object objKeyStore = methodGetInstance.invoke(null, null);

			Method methodSaw = clsKeyStore.getDeclaredMethod("saw",
					String.class);
			methodSaw.setAccessible(true);
			String[] keys = (String[]) methodSaw.invoke(objKeyStore, "VPN_");

			Class clsVpnProfile = Class
					.forName("com.android.internal.net.VpnProfile");
			Method methodDecode = clsVpnProfile.getDeclaredMethod("decode",
					String.class, byte[].class);
			methodDecode.setAccessible(true);

			Method methodGet = clsKeyStore.getDeclaredMethod("get",
					String.class);
			methodGet.setAccessible(true);

			Object byteArrayValue = methodGet.invoke(objKeyStore, "VPN_"
					+ keys[0]);

			Object objVpnProfile = methodDecode.invoke(null, keys[0],
					byteArrayValue);

			return objVpnProfile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Class getVpnProfileClass() {
		try {
			return Class.forName("com.android.internal.net.VpnProfile");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void connect(Context context, Object profile) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		Field fieldIConManager = null;

		try {
			fieldIConManager = cm.getClass().getDeclaredField("mService");
			fieldIConManager.setAccessible(true);
			Object objIConManager = fieldIConManager.get(cm);
			Class clsIConManager = Class.forName(objIConManager.getClass()
					.getName());

			Class clsVpnProfile = Class
					.forName("com.android.internal.net.VpnProfile");
			Method metStartLegacyVpn = clsIConManager.getDeclaredMethod(
					"startLegacyVpn", clsVpnProfile);
			metStartLegacyVpn.setAccessible(true);

			metStartLegacyVpn.invoke(objIConManager, profile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void disconnect(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		Field fieldIConManager = null;

		try {
			fieldIConManager = cm.getClass().getDeclaredField("mService");
			fieldIConManager.setAccessible(true);
			Object objIConManager = fieldIConManager.get(cm);
			Class clsIConManager = Class.forName(objIConManager.getClass()
					.getName());
			Method metPrepare = clsIConManager.getDeclaredMethod("prepareVpn",
					String.class, String.class);
			metPrepare.invoke(objIConManager, "[Legacy VPN]", "[Legacy VPN]");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
