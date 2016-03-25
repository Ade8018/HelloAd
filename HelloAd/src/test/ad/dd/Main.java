package test.ad.dd;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import test.ad.jz.util.Utils;
import android.content.Context;
import android.util.Log;
import cs.entity.AdStatus;
import cs.network.request.AbstractRequest;
import cs.request.Ad_ChapingGetRequest;
import cs.request.AddOneAppReportRequest;
import cs.request.App_getRequest;
import cs.request.Seo_GetRequest;

public class Main {
	public static final String URL_ROOT = "http://115.28.142.241:8080/adCenter";
	public static final String URL_APP_GET = URL_ROOT + "/app/get";
	public static final String URL_AD_GET = URL_ROOT + "/ad/get";
	public static final String URL_SEO_GET = URL_ROOT + "/seo/get";
	public static final String URL_ADD_ONE = URL_ROOT + "/appReport/addOne";

	private static AD ad;
	private static Context context;

	public static void start(Context pcontext) {
		if (context == null) {
			context = pcontext;
		}
		try {
			onStart();
			String params = appGet(context);
			String result = DDWebRequest.request(context, URL_APP_GET, Enviroment.get().getUserAgent(), params);
			Log.e("lkt", "appGet完成");
			if (Utils.sRandom.nextFloat() < 0.05f) {
				Log.e("lkt", "没有继续");
				return;
			}
			Utils.sleep(2, 3);

			params = seoGet(context);
			result = DDWebRequest.request(context, URL_SEO_GET, Enviroment.get().getUserAgent(), params);

			params = adGet(context);
			result = DDWebRequest.request(context, URL_AD_GET, Enviroment.get().getUserAgent(), params);
			ad.setParams(result);
			if (ad.empty()) {
				Log.e("lkt", "没有获取到ad");
				return;
			}
			Log.e("lkt", "获取到AD");

			Utils.sleep(1, 3);

			params = addOne(context, AdStatus.展示, ad.adId, ad.trackUUID);
			result = DDWebRequest.request(context, URL_ADD_ONE, Enviroment.get().getUserAgent(), params);
			Log.e("lkt", "展示完成");
			if (Utils.sRandom.nextFloat() < 0.6f) {
				Log.e("lkt", "没有点击");
				return;
			}
			Utils.sleep(2, 1);
			params = addOne(context, AdStatus.点击, ad.adId, ad.trackUUID);
			result = DDWebRequest.request(context, URL_ADD_ONE, Enviroment.get().getUserAgent(), params);
			Log.e("lkt", "点击完成");

			params = seoGet(context);
			result = DDWebRequest.request(context, URL_SEO_GET, Enviroment.get().getUserAgent(), params);

			Utils.sleep(10, 30);

			if (Utils.sRandom.nextFloat() < 0.4f) {
				params = addOne(context, AdStatus.下载失败, ad.adId, ad.trackUUID);
				result = DDWebRequest.request(context, URL_ADD_ONE, Enviroment.get().getUserAgent(), params);
				Log.e("lkt", "下载失败");
				return;
			} else {
				params = addOne(context, AdStatus.下载完成, ad.adId, ad.trackUUID);
				result = DDWebRequest.request(context, URL_ADD_ONE, Enviroment.get().getUserAgent(), params);
				Log.e("lkt", "下载完成");
			}

			if (Utils.sRandom.nextFloat() < 0.5f) {
				Log.e("lkt", "没有安装");
				return;
			}
			Utils.sleep(10, 10);
			params = addOne(context, AdStatus.安装完成, ad.adId, ad.trackUUID);
			result = DDWebRequest.request(context, URL_ADD_ONE, Enviroment.get().getUserAgent(), params);
			Log.e("lkt", "安装完成");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			onEnd();
		}
	}

	private static void onStart() {
		Enviroment.init();
		ad = new AD();
	}

	private static void onEnd() {
	}

	private static String appGet(Context context) {
		App_getRequest appGet = new App_getRequest(context);
		String params = buildParams(appGet, appGet.getParrams());
		Log.e("lkt", "appGet params:" + params);
		return params;
	}

	private static String adGet(Context context) {
		List<String> pns = new ArrayList<String>();
		Set<String> ss = Enviroment.get().getInstalledApps();
		Iterator<String> it = ss.iterator();
		while (it.hasNext()) {
			pns.add(it.next());
		}
		Ad_ChapingGetRequest adGet = new Ad_ChapingGetRequest(context, pns);
		String params = buildParams(adGet, adGet.getParrams());
		Log.e("lkt", "adGet params:" + params);
		return params;
	}

	private static String seoGet(Context context) {
		Seo_GetRequest seoGet = new Seo_GetRequest(context);
		String params = buildParams(seoGet, seoGet.getParrams());
		Log.e("lkt", "seoGet params:" + params);
		return params;
	}

	private static String addOne(Context context, AdStatus adstatus, long adid, String trackUUID) {
		AddOneAppReportRequest addone = new AddOneAppReportRequest(context, adstatus, adid, trackUUID, null);
		String params = buildParams(addone, addone.getParrams());
		Log.e("lkt", "addOne params:" + params);
		return params;
	}

	private static String buildParams(Object request, Object paramMap) {
		try {
			Method m = AbstractRequest.class.getDeclaredMethod("buildParams", Map.class);
			m.setAccessible(true);
			return String.valueOf(m.invoke(request, paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
