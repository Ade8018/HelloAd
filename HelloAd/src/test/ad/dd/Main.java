package test.ad.dd;

import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import test.ad.jz.util.Base64;
import android.content.Context;
import android.util.Log;
import cs.network.request.AbstractRequest;
import cs.request.Ad_ChapingGetRequest;
import cs.request.App_getRequest;

public class Main {
	private static Thread ddThread;
	private static boolean isRunning;

	public static void start() {
		if (isRunning) {
			Log.e("lkt", "ddThread isRunning");
			return;
		}

	}

	public static void appGet(Context context) {
		Enviroment.init();
		App_getRequest appGet = new App_getRequest(context);
		Log.e("lkt", "appGet params:"
				+ buildParams(appGet, appGet.getParrams()));
	}

	public static void adGet(Context context) {
		Enviroment.init();
		Ad_ChapingGetRequest adGet = new Ad_ChapingGetRequest(context, null);
		Log.e("lkt", "adGet params:" + buildParams(adGet, adGet.getParrams()));
	}

	private static String buildParams(Object request, Object paramMap) {
		try {
			Method m = AbstractRequest.class.getDeclaredMethod("buildParams",
					Map.class);
			m.setAccessible(true);
			return String.valueOf(m.invoke(request, paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
