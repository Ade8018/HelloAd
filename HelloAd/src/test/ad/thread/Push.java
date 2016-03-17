package test.ad.thread;

import test.ad.data.AdData;
import test.ad.entity.Action;
import test.ad.entity.Request;
import test.ad.log.L;
import test.ad.util.Http;
import test.ad.util.Utils;

public class Push implements Runnable {
	@Override
	public void run() {
		getAdList();
		Utils.sleep(1, 1);
		postPush();
		if (!isUserClick())
			return;
		Utils.sleep(2, 3);
		postStart();
		AdData.getCurrent().pushCount++;
	}

	private boolean isUserClick() {
		return Utils.random(0.5f);
	}

	private void getAdList() {
		Request r = new Request(AdData.getCurrent().base, AdData.getCurrent().pushAdId, "push", "1");
		String resp = Http.post(r);
		getAdId(resp);
		L.log(resp);
	}

	private void getAdId(String resp) {
		String adIds[] = Utils.getAdIds(resp);
		if (adIds != null && adIds.length > 0) {
			AdData.getCurrent().pushAdId = adIds[Utils.sRandom.nextInt(adIds.length)];
		}
	}

	private void postPush() {
		if (AdData.getCurrent().pushAdId == null) {
			L.log("can't push pushAD because of empty adId");
			return;
		}
		Action a = new Action(AdData.getCurrent().base, "push", "push", AdData.getCurrent().pushAdId);
		L.log(Http.post(a));
	}

	private void postStart() {
		if (AdData.getCurrent().pushAdId == null) {
			L.log("can't start pushAD because of empty adId");
			return;
		}
		Action a = new Action(AdData.getCurrent().base, "push", "start", AdData.getCurrent().pushAdId);
		L.log(Http.post(a));
	}

}
