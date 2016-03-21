package ad.hello;

import java.util.Set;

import test.ad.Main;
import test.ad.util.Utils;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.VpnService;
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
						Log.e("lkt", "线程已经在运行");
						return;
					}
					running = true;
					Helper.resetVpn(getApplicationContext());
					String ip = getIp();
					Log.e("lkt", "获取到ip:" + ip);
					SpHelper.saveIp(TimeService.this, ip);
					Main.startNew();
					Utils.sleep(59, 5);
					running = false;
				}
			}).start();
		}

		private String getIp() {
			String ip = null;
			int time = 1;
			while (ip == null) {
				Set<String> ips = SpHelper.getIps(TimeService.this);
				Utils.sleep(5, 1);
				Log.e("lkt", "第" + time + "次获取ip");
				if (time == 2) {
					Helper.resetVpn(getApplicationContext());
					time = 0;
				}
				time++;
				ip = Helper.GetNetIp();
				if (ips != null && ips.contains(ips)) {
					Log.e("lkt", "ip:" + ip + "已存在，将再次更换ip");
					Helper.resetVpn(getApplicationContext());
					ip = null;
				}
			}
			return ip;
		}
	}
}
