package ad.hello;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SpHelper {
	public static final String KEY_NO_AD = "no_ad";
	public static final String KEY_COMPLETE = "complete";

	public synchronized static Set<String> getIps(Context context) {
		SharedPreferences sp = context.getSharedPreferences("hello", Context.MODE_PRIVATE);
		return sp.getStringSet("ips", null);
	}

	public synchronized static void saveIp(Context context, String ip) {
		SharedPreferences sp = context.getSharedPreferences("hello", Context.MODE_PRIVATE);
		Set<String> s = sp.getStringSet("ips", null);
		if (s == null) {
			s = new HashSet<String>();
		}
		s.add(ip);
		Log.e("lkt", "remove ips result:" + sp.edit().remove("ips").commit());
		Log.e("lkt", "commit ips result:" + sp.edit().putStringSet("ips", s).commit());
	}

	public synchronized static void inc(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences("hello", Context.MODE_PRIVATE);
		int value = sp.getInt(key, 0);
		value++;
		sp.edit().putInt(key, value).commit();
	}

	public synchronized static int getNoAdCount(Context context) {
		SharedPreferences sp = context.getSharedPreferences("hello", Context.MODE_PRIVATE);
		return sp.getInt(KEY_NO_AD, 0);
	}

	public synchronized static int getCompleteCount(Context context) {
		SharedPreferences sp = context.getSharedPreferences("hello", Context.MODE_PRIVATE);
		return sp.getInt(KEY_COMPLETE, 0);
	}
}
