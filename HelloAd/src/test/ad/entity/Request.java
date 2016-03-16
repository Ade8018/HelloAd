package test.ad.entity;

import test.ad.util.Utils;

public class Request extends Base {
	String sendCount;// 请求的广告数量

	public Request() {
	}

	public Request(Base b, String adId, String adType, String sendCount) {
		super();
		this.appId = b.appId;
		this.uuid = b.uuid;
		this.ua = b.ua;
		this.os = b.os;
		this.packageName = b.packageName;
		this.sdkVersion = b.sdkVersion;
		this.province = b.province;
		this.carrier = b.carrier;
		this.imsi = b.imsi;
		this.mac = b.mac;
		this.adId = adId;
		this.adType = adType;
		this.sendCount = sendCount;
	}

	@Override
	public String toString() {
		return super.toString() + "&sendCount=" + (sendCount == null ? "" : sendCount);
	}

	public static Request get(String adType, String sendCount) {
		Request r = new Request();
		r.appId = "01hBO810";
		r.uuid = Utils.getRandomIMEI();
		r.ua = Utils.getRandomUA();
		r.os = Utils.getRandomOSVersion();
		r.packageName = Utils.getRandomPackageName();
		r.imsi = Utils.getRandomIMSI();
		r.carrier = Utils.getCarrierByIMSI(r.imsi);
		r.mac = Utils.getRandomMAC();
		r.adType = adType;
		r.sendCount = sendCount;
		return r;
	}
}
