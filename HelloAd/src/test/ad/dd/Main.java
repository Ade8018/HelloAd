package test.ad.dd;

import java.lang.reflect.Field;

import cs.network.configs.Config;

public class Main {
	public static void main(String[] args) {
		setAppID();
		setAppPackageName();
		setClientUUID();
		setImsi();
	}

	public static void start() {
		
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

	protected static void setImsi() {
		try {
			// imsi
			Field f = Config.class.getDeclaredField("imsi");
			f.setAccessible(true);
			f.set(null, "460004960567588");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void setClientUUID() {
		// clientUUID
		try {
			Field f = Config.class.getDeclaredField("clientUUID");
			f.setAccessible(true);
			f.set(null, "507835236185671");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void setAppPackageName() {
		// appPackageName
		try {
			Field f = Config.class.getDeclaredField("appPackageName");
			f.setAccessible(true);
			f.set(null, "lkt.testnativeeo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void setAppID() {
		// appID
		try {
			Field f = Config.class.getDeclaredField("appID");
			f.setAccessible(true);
			f.set(null, "23044");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}