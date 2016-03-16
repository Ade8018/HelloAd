package test.ad.thread;

import test.ad.data.AdData;
import test.ad.entity.Request;
import test.ad.log.L;
import test.ad.util.Http;

public class Desktop implements Runnable {

	@Override
	public void run() {
		getAdList();
	}

	/**
	 * 如果已经点击了某个Ad，则要将该adId填入参数
	 */
	private void getAdList() {
		Request r = new Request(AdData.getCurrent().base, AdData.getCurrent().DesktopAdId, "desktop", "9");
		L.log(Http.post(r));
	}
}
