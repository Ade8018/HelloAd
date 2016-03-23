package test.ad.jz.thread;

import test.ad.jz.data.AdData;
import test.ad.jz.entity.Request;
import test.ad.jz.log.L;
import test.ad.jz.util.Http;

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
