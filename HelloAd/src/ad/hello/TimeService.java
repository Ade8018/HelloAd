package ad.hello;

import java.util.Set;

import test.ad.Main;
import test.ad.util.Utils;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class TimeService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private TimeReceiver receiver;

	@Override
	public void onCreate() {
		Log.e("lkt", "onCreate");
		SpHelper.init(this);
		receiver = new TimeReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_TIME_TICK);
		registerReceiver(receiver, filter);
	}

	@Override
	public void onDestroy() {
		Log.e("lkt", "onDestroy");
		unregisterReceiver(receiver);
		startService(new Intent(this, TimeService.class));
	}

	class TimeReceiver extends BroadcastReceiver {
		private boolean running = false;

		@Override
		public void onReceive(Context context, Intent intent) {
			Log.e("lkt", "onReceive");
			new Thread(new Runnable() {
				@Override
				public void run() {
					if (running) {
						return;
					}
					running = true;
					int min = SpHelper.getMinute();
					SpHelper.incMinute();
					if (min % 5 == 0) {
						MainActivity.setMobileDataEnabled(TimeService.this,
								false);
						MainActivity.setMobileDataEnabled(TimeService.this,
								true);
						String ip = getIp();
						Log.e("lkt", "获取到ip:" + ip);
						SpHelper.saveIp(ip);
						Main.startNew();
					} else {
						Main.goon();
					}
					running = false;
				}
			}).start();
		}

		private String getIp() {
			String ip = null;
			while (ip == null) {
				Set<String> ips = SpHelper.getIps();
				Utils.sleep(5, 5);
				ip = Helper.GetNetIp();
				if (ips != null && ips.contains(ips)) {
					MainActivity.setMobileDataEnabled(TimeService.this, false);
					MainActivity.setMobileDataEnabled(TimeService.this, true);
					ip = null;
				}
			}
			return ip;
		}
	}
}
