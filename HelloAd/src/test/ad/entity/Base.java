package test.ad.entity;

import test.ad.util.Utils;

public class Base {
	public static final String SDK_VERSION = "2.6.4";
	public String appId;
	public String uuid;// imei
	public String ua;// GiONEE@GN9006
	public String os;// 4.4.2
	public String safe = "1";// unknow default 1
	public String versionCode = "1.0";// unknow default 1
	public String packageName;
	public String sdkVersion = SDK_VERSION;
	public String province;// 广东
	public String carrier;// cmcc
	public String imsi;// 460020103263174
	public String mac;// 00:08:22:24:fe:fb
	public String adId;// 首次获取广告为空
	public String adType;// chaping

	public Base() {
	}

	public static Base get() {
		Base b = new Base();
		b.appId = "01hBO810";
		b.uuid = Utils.getRandomIMEI();
		b.ua = Utils.getRandomUA();
		b.os = Utils.getRandomOSVersion();
		b.packageName = Utils.getRandomPackageName();
		b.imsi = Utils.getRandomIMSI();
		b.carrier = Utils.getCarrierByIMSI(b.imsi);
		b.mac = Utils.getRandomMAC();
		return b;
	}

	@Override
	public String toString() {
		return "appId=" + (appId == null ? "" : appId) + "&uuid=" + (uuid == null ? "" : uuid) + "&ua=" + (ua == null ? "" : ua) + "&os=" + (os == null ? "" : os) + "&safe="
				+ (safe == null ? "" : safe) + "&versionCode=" + (versionCode == null ? "" : versionCode) + "&packageName=" + (packageName == null ? "" : packageName) + "&sdkVersion="
				+ (sdkVersion == null ? "" : sdkVersion) + "&province=" + (province == null ? "" : province) + "&carrier=" + (carrier == null ? "" : carrier) + "&imsi=" + (imsi == null ? "" : imsi)
				+ "&mac=" + (mac == null ? "" : mac) + "&adId=" + (adId == null ? "" : adId) + "&adType=" + (adType == null ? "" : adType);
	}
}
