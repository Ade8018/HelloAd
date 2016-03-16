package ad.hello;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

import test.ad.util.Utils;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			tvIp.setText(tvIp.getText() + "\n" + msg.obj.toString());
		};
	};
	private TextView tvIp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SpHelper.init(this);
		setContentView(R.layout.activity_main);
		findViewById(R.id.btn_on).setOnClickListener(this);
		findViewById(R.id.btn_off).setOnClickListener(this);
		tvIp = (TextView) findViewById(R.id.tv_ip);

		Set<String> ips = SpHelper.getIps();
		if (ips != null) {
			Iterator<String> it = ips.iterator();
			String result = "";
			while (it.hasNext()) {
				result += it.next() + "\n";
			}
			tvIp.setText(result);
		}
		startService(new Intent(this, TimeService.class));
	}

	private PowerManager pm;
	private WakeLock wl;

	@Override
	protected void onResume() {
		super.onResume();
		pm = (PowerManager) getSystemService(POWER_SERVICE);
		wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "wake lock");
		wl.acquire();
	}

	@Override
	protected void onPause() {
		wl.release();
		super.onPause();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_off:
			break;
		case R.id.btn_on:
			break;
		default:
			break;
		}
	}

	private void getIp() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String ip = null;
				while (ip == null) {
					Utils.sleep(5, 5);
					ip = Helper.GetNetIp();
				}
				handler.sendMessage(handler.obtainMessage(0, ip));
			}
		}).start();
	}

	public static void setMobileDataEnabled(Context context, boolean enabled) {// 开和关
		try {
			final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			final Class conmanClass = Class.forName(conman.getClass().getName());
			final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
			connectivityManagerField.setAccessible(true);
			final Object connectivityManager = connectivityManagerField.get(conman);
			final Class connectivityManagerClass = Class.forName(connectivityManager.getClass().getName());
			final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
			setMobileDataEnabledMethod.setAccessible(true);
			setMobileDataEnabledMethod.invoke(connectivityManager, enabled);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
