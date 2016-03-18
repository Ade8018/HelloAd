package test.ad.thread;

import test.ad.data.AdData;
import test.ad.entity.Action;
import test.ad.entity.Request;
import test.ad.log.L;
import test.ad.util.Http;
import test.ad.util.Utils;

public class Chaping implements Runnable {
	@Override
	public void run() {
		getAdList();
		if (!isUserClickAd()) {
			L.log("user didn't click ad");
			return;
		}
		Utils.sleep(2, 2);
		getAdList();
		Utils.sleep(8, 8);
		postAdAppDownloaded();
		if (!isUserInstallApp()) {
			return;
		}
		Utils.sleep(6, 6);
		postAdAppInstalled();
		Utils.sleep(1, 1);
		postAdAppStarted();
		AdData.getCurrent().activeCount++;
	}

	private void postAdAppDownloaded() {
		if (AdData.getCurrent().ChapingAdId == null) {
			L.log("can't download chapingAD because of empty adId");
			return;
		}
		Action a = new Action(AdData.getCurrent().base, "chaping", "download",
				AdData.getCurrent().ChapingAdId);
		L.log(Http.post(a));
	}

	private void postAdAppInstalled() {
		if (AdData.getCurrent().ChapingAdId == null) {
			L.log("can't install chapingAD because of empty adId");
			return;
		}
		Action a = new Action(AdData.getCurrent().base, "chaping", "install",
				AdData.getCurrent().ChapingAdId);
		L.log(Http.post(a));
	}

	private void postAdAppStarted() {
		if (AdData.getCurrent().ChapingAdId == null) {
			L.log("can't start chapingAD because of empty adId");
			return;
		}
		Action a = new Action(AdData.getCurrent().base, "chaping", "start",
				AdData.getCurrent().ChapingAdId);
		L.log(Http.post(a));
	}

	/**
	 * 如果已经点击了某个Ad，则要将该adId填入参数
	 */
	private void getAdList() {
		Request r = new Request(AdData.getCurrent().base,
				AdData.getCurrent().ChapingAdId, "chaping",
				AdData.getCurrent().ChapingAdId == null ? "3" : "2");
		String resp = Http.post(r);
		getAdId(resp);
		getProvince(resp);
		L.log(resp);
	}

	private void getProvince(String resp) {
		if (AdData.getCurrent().base.province == null) {
			AdData.getCurrent().base.province = Utils.getProvince(resp);
		}
	}

	private void getAdId(String resp) {
		String adIds[] = Utils.getAdIds(resp);
		if (adIds != null && adIds.length > 0) {
			AdData.getCurrent().ChapingAdId = adIds[Utils.sRandom
					.nextInt(adIds.length)];
		}
	}

	private boolean isUserClickAd() {
		return Utils.random(0.2f);
	}

	private boolean isUserInstallApp() {
		return Utils.random(0.2f);
	}
}
