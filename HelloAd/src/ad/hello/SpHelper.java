package ad.hello;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;

public class SpHelper {
	static SharedPreferences sp;

	public static void init(Context context) {
		sp = context.getSharedPreferences("hello", Context.MODE_PRIVATE);
	}

	public static int getMinute() {
		return sp.getInt("min", 0);
	}

	public static void incMinute() {
		int min = getMinute();
		sp.edit().putInt("min", ++min).commit();
	}

	public static Set<String> getIps() {
		return sp.getStringSet("ips", null);
	}

	public static void saveIp(String ip) {
		Set<String> s = getIps();
		if (s == null) {
			s = new HashSet<String>();
		}
		s.add(ip);
		sp.edit().putStringSet("ips", s).commit();
	}
}
