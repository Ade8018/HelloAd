package ad.hello;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SpHelper {

	public synchronized static Set<String> getIps(Context context) {
		SharedPreferences sp = context.getSharedPreferences("hello",
				Context.MODE_PRIVATE);
		return sp.getStringSet("ips", null);
	}

	public synchronized static void saveIp(Context context, String ip) {
		SharedPreferences sp = context.getSharedPreferences("hello",
				Context.MODE_PRIVATE);
		Set<String> s = sp.getStringSet("ips", null);
		if (s == null) {
			s = new HashSet<String>();
		}
		s.add(ip);
		Log.e("lkt", "remove ips result:" + sp.edit().remove("ips").commit());
		Log.e("lkt", "commit ips result:"
				+ sp.edit().putStringSet("ips", s).commit());
	}
}
