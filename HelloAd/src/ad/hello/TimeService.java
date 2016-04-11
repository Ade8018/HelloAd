package ad.hello;

import java.util.Set;

import test.ad.Time;
import test.ad.dd.Main;
import test.ad.jz.util.Utils;
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
					if (Time.isThisTimeOk()) {
						Utils.sleep(1, 50);
						Helper.resetVpn(getApplicationContext());
						String ip = getIp();
						Log.e("lkt", "获取到ip:" + ip);
						SpHelper.saveIp(TimeService.this, ip);
//						test.ad.jz.Main.startNew();
						Main.start(getApplicationContext());
						Utils.sleep(60, 5);
					} else {
						Log.e("lkt", "新增限制");
					}
					running = false;
				}
			}).start();
		}

		private String getIp() {
			String ip = null;
			int time = 0;
			while (ip == null) {
				Utils.sleep(10, 1);
				if (time == 3) {
					Helper.resetVpn(getApplicationContext());
					time = 0;
				}
				time++;
				Log.e("lkt", "第" + time + "次获取ip");
				ip = Helper.GetNetIp();
				if (ip == null) {
					continue;
				}
				Set<String> ips = SpHelper.getIps(TimeService.this);
				if (ips != null && ips.contains(ip)) {
					Log.e("lkt", "ip:" + ip + "已存在，将再次更换ip");
					ip = null;
				}
			}
			return ip;
		}
	}
}
