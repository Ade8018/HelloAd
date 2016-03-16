package test.ad.data;

import test.ad.entity.Base;

public class AdData {
	private static AdData sInstance;
	public String pushAdId;
	public String ChapingAdId;
	public String DesktopAdId;
	public String NewDskAdId;
	public Base base;
	public int activeCount;
	public int pushCount;

	public static AdData getCurrent() {
		return sInstance;
	}

	public static AdData create() {
		sInstance = new AdData();
		return sInstance;
	}

	public static void destory() {
		sInstance = null;
	}
}
