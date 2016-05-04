package test.ad.dd;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import android.util.Log;

import test.ad.jz.util.Utils;
import cs.network.configs.Config;

public class Enviroment {
	private String imei;
	private String imsi;
	private String packageName;
	private static String appIDs[] = new String[] { "23044", "25572", "23044",
			"23044", "23044", "23044", "23044", "23044", "23044", "23044",
			"23044", "23044", "23044", "23044", "23044" };
	private static Map<String, String> id2pwd = new HashMap<String, String>();
	private String appID;
	private String appPwd;
	private Set<String> installedApps;
	private Random rand = new Random();
	private String userAgent;

	{
		id2pwd.put("23044", "WMAQLoHHmpsmxu5x");
		id2pwd.put("25572", "Y9hE83x4u5211c3a");
	}

	private static Enviroment s;

	private Enviroment() {
		imei = getIMEI();
		imsi = getImsi();
		appID = getRandomAppID();
		appPwd = getPwdById(appID);

		packageName = Utils.getPackageNameByAppId(appID);

		userAgent = DDAgent.agents[rand.nextInt(DDAgent.agents.length)];

		installedApps = new HashSet<String>();
		installedApps.add(packageName);
		int sec = rand.nextInt(2);
		int need = rand.nextInt(2) + 1;
		int others = rand.nextInt(10) + 10;
		for (int i = 0; i < sec; i++) {
			installedApps.add(DDpackages.secure[rand
					.nextInt(DDpackages.secure.length)]);
		}
		for (int i = 0; i < need; i++) {
			installedApps.add(DDpackages.need[rand
					.nextInt(DDpackages.need.length)]);
		}
		for (int i = 0; i < others; i++) {
			installedApps.add(DDpackages.others[rand
					.nextInt(DDpackages.others.length)]);
		}
	}

	private String getRandomAppID() {
		return appIDs[rand.nextInt(appIDs.length)];
	}

	public static void init() {
		s = new Enviroment();
		Log.e("lkt", "appid:" + s.appID + "  pwd:" + s.appPwd);
		setAppID(s.appID);
		setAppPackageName(s.packageName);
		setClientUUID(s.imei);
		setImsi(s.imsi);
		setAppPassword(s.appPwd);
	}

	private static String getPwdById(String appid) {
		return id2pwd.get(appid);
	}

	public static Enviroment get() {
		return s;
	}

	protected static void setImsi(String imsi) {
		try {
			// imsi
			Field f = Config.class.getDeclaredField("imsi");
			f.setAccessible(true);
			f.set(null, imsi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setAppPassword(String appPwd) {
		try {
			// imsi
			Field f = Config.class.getDeclaredField("appPassword");
			f.setAccessible(true);
			f.set(null, appPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setClientUUID(String imei) {
		// clientUUID
		try {
			Field f = Config.class.getDeclaredField("clientUUID");
			f.setAccessible(true);
			f.set(null, imei);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setAppPackageName(String packageName) {
		// appPackageName
		try {
			Field f = Config.class.getDeclaredField("appPackageName");
			f.setAccessible(true);
			f.set(null, packageName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setAppID(String appID) {
		// appID
		try {
			Field f = Config.class.getDeclaredField("appID");
			f.setAccessible(true);
			f.set(null, appID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getIMEI() {// calculator IMEI
		int r1 = 1000000 + new java.util.Random().nextInt(9000000);
		int r2 = 1000000 + new java.util.Random().nextInt(9000000);
		String input = r1 + "" + r2;
		char[] ch = input.toCharArray();
		int a = 0, b = 0;
		for (int i = 0; i < ch.length; i++) {
			int tt = Integer.parseInt(ch[i] + "");
			if (i % 2 == 0) {
				a = a + tt;
			} else {
				int temp = tt * 2;
				b = b + temp / 10 + temp % 10;
			}
		}
		int last = (a + b) % 10;
		if (last == 0) {
			last = 0;
		} else {
			last = 10 - last;
		}
		return input + last;
	}

	private static String getImsi() {
		// 460022535025034
		String title = "4600";
		int second = 0;
		do {
			second = new java.util.Random().nextInt(8);
		} while (second == 4);
		int r1 = 10000 + new java.util.Random().nextInt(90000);
		int r2 = 10000 + new java.util.Random().nextInt(90000);
		return title + "" + second + "" + r1 + "" + r2;
	}

	private static String getMac() {
		char[] char1 = "abcdef".toCharArray();
		char[] char2 = "0123456789".toCharArray();
		StringBuffer mBuffer = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			int t = new java.util.Random().nextInt(char1.length);
			int y = new java.util.Random().nextInt(char2.length);
			int key = new java.util.Random().nextInt(2);
			if (key == 0) {
				mBuffer.append(char2[y]).append(char1[t]);
			} else {
				mBuffer.append(char1[t]).append(char2[y]);
			}

			if (i != 5) {
				mBuffer.append(":");
			}
		}
		return mBuffer.toString();
	}

	public String getUserAgent() {
		return userAgent;
	}

	public Set<String> getInstalledApps() {
		return installedApps;
	}

}
