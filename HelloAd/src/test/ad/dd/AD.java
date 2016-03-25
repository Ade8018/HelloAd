package test.ad.dd;

import android.util.Log;

public class AD {
	long adId;
	String trackUUID;

	public void setParams(String str) {
		if (str == null) {
			return;
		}
		int indexId = str.indexOf("adID");
		if (indexId < 0) {
			return;
		}
		int indexTD = str.indexOf("trackUUID");
		if (indexTD < 0) {
			return;
		}
		try {
			// adid
			String sub = str.substring(indexId, indexId + 20);
			int indexD = sub.indexOf(",");
			String idStr = sub.substring(8, indexD);
			adId = Long.parseLong(idStr);
			Log.e("lkt", "adID:" + adId);
			// trackUUID
			sub = str.substring(indexTD);
			indexD = sub.indexOf(",");
			trackUUID = sub.substring(14, indexD - 1);
			Log.e("lkt", "trackUUID:" + trackUUID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean empty() {
		return adId == 0 || trackUUID == null;
	}
}
