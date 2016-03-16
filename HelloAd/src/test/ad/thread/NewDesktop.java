package test.ad.thread;

import test.ad.data.AdData;
import test.ad.entity.Action;
import test.ad.entity.Request;
import test.ad.log.L;
import test.ad.util.Http;
import test.ad.util.Utils;

public class NewDesktop implements Runnable {
	@Override
	public void run() {
		// 第一次一定要获取list，之后的话可以随机执行以下方法中的一个
		AdData data = AdData.getCurrent();
		if (data.NewDskAdId == null) {
			getAdList();
		} else {
			if (Utils.random(0.5f)) {
				getAdList();
			} else {
				postPush();
			}
		}
	}

	private void getAdList() {
		Request r = new Request(AdData.getCurrent().base, AdData.getCurrent().NewDskAdId, "newDesktop", "8");
		String result = Http.post(r);
		getAdId(result);
		L.log(result);
	}

	private void getAdId(String resp) {
		String adIds[] = Utils.getAdIds(resp);
		if (adIds != null && adIds.length > 0) {
			AdData.getCurrent().NewDskAdId = adIds[Utils.sRandom.nextInt(adIds.length)];
		}
	}

	private void postPush() {
		if (AdData.getCurrent().NewDskAdId == null) {
			L.log("can't push newDesktopAD because of empty adId");
			return;
		}
		Action a = new Action(AdData.getCurrent().base, "newDesktop", "push", AdData.getCurrent().NewDskAdId);
		String result = Http.post(a);
		L.log(result);
	}

}
