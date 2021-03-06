package test.ad.jz;

import android.util.Log;
import test.ad.jz.data.AdData;
import test.ad.jz.entity.Base;
import test.ad.jz.thread.Chaping;
import test.ad.jz.thread.Desktop;
import test.ad.jz.thread.NewDesktop;
import test.ad.jz.thread.Push;
import test.ad.jz.util.ExecutorUtil;
import test.ad.jz.util.Utils;

public class Main {

	public static void startNew() {
		Log.e("lkt", "开启新任务");
		first = true;
		AdData data = AdData.create();
		data.base = Base.get();
		goon();
	}

	static boolean first = true;

	public static void goon() {
		Log.e("lkt", "继续执行广告");
		if (AdData.getCurrent() == null) {
			Log.e("lkt", "当前广告为空");
			return;
		}
		for (int i = 0; i < 8; i++) {
			if (first) {
				first = false;
				ExecutorUtil.post(new Chaping());
			} else {
				Runnable r = getRandomRun();
				if (r != null) {
					ExecutorUtil.post(r);
				}
			}
			if (AdData.getCurrent().activeCount >= 3
					|| AdData.getCurrent().pushCount >= 30) {
				break;
			}
			Utils.sleep(2, 8);
		}
	}

	public static Runnable getRandomRun() {
		float f = Utils.sRandom.nextFloat();
		if (f < 0.1f) {
			return new Chaping();
		} else if (f >= 0.1f && f < 0.20f) {
			return new Push();
		} else if (f >= 0.20f && f < 0.25f) {
			return new Desktop();
		} else if (f >= 0.25f && f < 0.30f) {
			return new NewDesktop();
		}
		return null;
	}
}
