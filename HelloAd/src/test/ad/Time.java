package test.ad;

import java.util.Calendar;

import test.ad.jz.util.Utils;

public class Time {
	public static boolean isThisTimeOk() {
		Calendar c = Calendar.getInstance();
		int h = c.get(Calendar.HOUR_OF_DAY);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		float i = 1.5f;
		if (weekday == 6 || weekday == 7) {
			i = 2;
		}
		if (weekday == 8) {
			i = 1.8f;
		}
		if (h >= 0 && h <= 1) {
			return Utils.sRandom.nextFloat() < (0.25f * i);
		}
		if (h > 1 && h <= 5) {
			return Utils.sRandom.nextFloat() < (0.05f * i);
		}
		if (h > 5 && h < 9) {
			return Utils.sRandom.nextFloat() < (0.25f * i);
		}
		if (h >= 9 && h <= 18) {
			return Utils.sRandom.nextFloat() < (0.4f * i);
		}
		if (h > 18) {
			return Utils.sRandom.nextFloat() < (0.5f * i);
		}
		return false;
	}
}
