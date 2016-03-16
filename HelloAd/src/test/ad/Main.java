package test.ad;

import android.util.Log;
import test.ad.data.AdData;
import test.ad.entity.Base;
import test.ad.thread.Chaping;
import test.ad.thread.Desktop;
import test.ad.thread.NewDesktop;
import test.ad.thread.Push;
import test.ad.util.ExecutorUtil;
import test.ad.util.Utils;

public class Main {

	public static void startNew() {
		Log.e("lkt", "开启新任务");
		AdData data = AdData.create();
		data.base = Base.get();
		goon();
	}

	public static void goon() {
		Log.e("lkt", "继续执行广告");
		if (AdData.getCurrent() == null) {
			return;
		}
		long start = System.currentTimeMillis();
		boolean first = true;
		for (int i = 0; i < 8; i++) {
			if (first) {
				first = false;
				ExecutorUtil.post(new Chaping());
			} else {
				ExecutorUtil.post(getRandomRun());
			}
			if (System.currentTimeMillis() - start < 1 * 60 * 1000) {
				Utils.sleep(2, 8);
			} else {
				Utils.sleep(30, 270);
			}
			if (AdData.getCurrent().activeCount >= 3 || AdData.getCurrent().pushCount >= 30) {
				break;
			}
			Utils.sleep(2, 3);
		}
	}

	public static Runnable getRandomRun() {
		float f = Utils.sRandom.nextFloat();
		if (f < 0.1f) {
			return new Chaping();
		} else if (f >= 0.1f && f < 0.45f) {
			return new Push();
		} else if (f >= 0.45f && f < 0.75f) {
			return new Desktop();
		}
		return new NewDesktop();
	}
}
