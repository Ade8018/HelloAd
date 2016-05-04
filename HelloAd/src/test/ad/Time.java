package test.ad;

import java.util.Calendar;

import test.ad.jz.util.Utils;

public class Time {
	public static boolean isThisTimeOk() {
		Calendar c = Calendar.getInstance();
		int h = c.get(Calendar.HOUR_OF_DAY);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		int i = 1;
		if (weekday == 6 || weekday == 7) {
			i = 2;
		}
		if (h >= 0 && h <= 1) {
			return Utils.sRandom.nextFloat() < (0.25f * i);
		}
		if (h > 1 && h <= 5) {
			return Utils.sRandom.nextFloat() < (0.1f * i);
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
