package test.ad;

import java.util.Calendar;

import test.ad.jz.util.Utils;

public class Time {
	public static boolean isThisTimeOk() {
		Calendar c = Calendar.getInstance();
		int h = c.get(Calendar.HOUR_OF_DAY);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		int i = 1;
		if (weekday == 1 || weekday == 6 || weekday == 7) {
			i = 2;
		}
		if (h >= 0 && h <= 2) {
			return Utils.sRandom.nextFloat() < (0.1f * i);
		}
		if (h > 2 && h <= 5) {
			return Utils.sRandom.nextFloat() < (0.04f * i);
		}
		if (h > 5 && h < 9) {
			return Utils.sRandom.nextFloat() < (0.1f * i);
		}
		if (h >= 9 && h <= 18) {
			return Utils.sRandom.nextFloat() < (0.3f * i);
		}
		if (h > 18) {
			return Utils.sRandom.nextFloat() < (0.4f * i);
		}
		return false;
	}
}
