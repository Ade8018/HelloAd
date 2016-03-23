package test.ad.jz.entity;

public class Action extends Base {
	String action;
	String adType1;

	@Override
	public String toString() {
		return super.toString() + "&action=" + (action == null ? "" : action)
				+ "&adType1=" + (adType1 == null ? "" : adType1);
	}

	public Action(Base b,String adType, String action,String adId) {
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
		this.action = action;
		this.adType = adType;
	}
}
