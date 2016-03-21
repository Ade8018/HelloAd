package ad.hello;

import java.util.Iterator;
import java.util.Set;

import test.ad.util.Utils;
import android.app.Activity;
import android.content.Intent;
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
		setContentView(R.layout.activity_main);
		findViewById(R.id.btn_on).setOnClickListener(this);
		findViewById(R.id.btn_off).setOnClickListener(this);
		tvIp = (TextView) findViewById(R.id.tv_ip);

		Set<String> ips = SpHelper.getIps(this);
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
}
