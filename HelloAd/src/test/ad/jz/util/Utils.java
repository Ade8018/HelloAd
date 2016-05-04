package test.ad.jz.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Utils {
	public static Random sRandom = new Random();
	public static String[] all = new String[] { "46000", "46002", "46007",
			"46001", "46006", "46003", "46005" };

	public static String getRandomIMEI() {
		// 例 507835236185671
		String result = "";
		for (int i = 0; i < "507835236185671".length(); i++) {
			result += sRandom.nextInt(10);
		}
		return result;
	}

	public static String getRandomIMSI() {
		// 460020103263174
		String result = all[sRandom.nextInt(all.length)];
		for (int i = 0; i < "0103263174".length(); i++) {
			result += sRandom.nextInt(10);
		}
		return result;
	}

	public static String getCarrierByIMSI(String imsi) {
		// cucc "46001", "46006"
		// ctcc "46003", "46005"
		// cmcc 46002 46000 46007
		if (imsi.startsWith("46001") || imsi.startsWith("46006")) {
			return "cucc";
		} else if (imsi.startsWith("46003") || imsi.startsWith("46005")) {
			return "ctcc";
		} else {
			return "cmcc";
		}
	}

	public static String getRandomMAC() {
		// 00:08:22:24:fe:fb
		String result = "";
		String srcs[] = "00:08:22:24:fe:fb".split(":");
		for (int i = 0; i < srcs.length; i++) {
			for (int j = 0; j < 2; j++) {
				result += Integer.toHexString(sRandom.nextInt(16));
			}
			if (i < srcs.length - 1) {
				result += ":";
			}
		}
		return result;
	}

	public static String[] oss = new String[] { "4.4.2", "4.4.3", "4.4.4",
			"5.0.0", "5.0.1", "5.1.1" };

	public static String getRandomOSVersion() {
		return oss[sRandom.nextInt(oss.length)];
	}

	public static String[] pns = new String[] { "upumk.plgl.wwfie",
			"naqjy.posmu.o", "mwyr.k.uxhm", "vvg.chi.lgomo", "gdgc.yo.ekdyn",
			"x.cbn.ruu", "djof.avpyy.yowl", "hdayu.tqpf.xn", "cvwbq.ujwuj.oxu",
			"rwilo.i.hu", "opw.oqn.sxk", "w.wyj.av", "ym.rggr.sm",
			"ilv.ps.afam", "ldp.de.rvs", "val.uiv.yldbb", "f.f.r", "t.f.yhg",
			"ufa.q.tqr", "f.ojqet.gi", "opw.oqn.sxk", "w.wyj.av", "ym.rggr.sm",
			"ilv.ps.afam", "ldp.de.rvs", "val.uiv.yldbb", "f.f.r", "t.f.yhg",
			"ufa.q.tqr", "f.ojqet.gi", "opw.oqn.sxk", "w.wyj.av",
			"opw.oqn.sxk", "w.wyj.av" };

	// , "na.p.v", "wjjg.vd.sep",
	// "lxypt.xkbpv.appl", "igje.hqfwn.fmeo", "n.l.usbhh", "ft.sl.jaaqn",
	// "wrcvc.t.g", "majvg.tmkxv.khusa", "grsgv.g.oudsc", "a.uqlv.hfy",
	// "i.busm.o", "bwd.gw.gbot", "ofk.dcgip.wt", "kim.cjc.gqnb",
	// "yddrf.id.a", "mbwjp.kb.ngtw", "fcnns.mccon.u", "tcx.yk.oljln",
	// "basy.csg.nq", "if.wdm.yttk", "r.sohq.vwd", "jmlj.m.mb",
	// "inf.fwn.h", "galcg.nep.u", "ocwa.yxo.cjpy", "ls.lx.bvac",
	// "tkn.sdb.g", "meirg.rc.h", "s.wxtjk.ybaw", "jatg.s.p",
	// "npru.gtcqt.hwq", "v.w.k"};

	// , "qbykx.vexa.oaxr", "tpy.ss.ai",
	// "lgi.iulkt.fm", "tami.cthv.feu", "wq.ossoj.mtel", "rsb.kvlr.wsms",
	// "lnl.o.bpax", "bevqf.x.cx", "hg.hklw.xjsk", "kmcuw.bxfkp.exa",
	// "blhee.jtaml.h", "iorr.jl.qcs", "xkbbi.auia.xq", "r.xddyo.tphuk",
	// "d.gag.r", "jyu.ke.ruvan", "hm.n.rdvsw", "qlj.c.wf",
	// "kkbo.n.tpntm", "duoyw.hfvwu.hhwp", "x.ph.i", "eh.fpb.y",
	// "vadjg.em.v", "bfkle.puhb.usrj", "udk.jn.v", "xjjxx.k.ol",
	// "qlfp.uefie.a", "ufag.duyr.uyv", "cj.xxlum.q", "opnrg.klt.hsgr",
	// "vpehn.qad.wesub", "hamtw.xc.ribne", "ghitg.afppw.sghjw",
	// "w.xwtx.gsq", "rvao.evlvj.fg", "n.phmd.eou", "cxg.ts.ie",
	// "ea.py.ki", "plwf.w.yx", "v.x.bpjyb", "gq.fbyu.mfqte",
	// "qynat.uxj.pulj", "h.adk.anl", "arjwc.nrq.nsy", "u.uvi.xyavd",
	// "wh.ptmp.ussv", "hbx.yr.a", "uln.g.j", "x.kuma.b", "ckcvv.ixm.j",
	// "mqcj.akoax.c", "h.i.tv", "m.ue.d", "xxyb.hplw.feg", "s.rvlqy.kil",
	// "ufwk.vpchw.pee" };

	public static String getRandomPackageNameFix() {
		return pns[sRandom.nextInt(pns.length)];
	}

	private static Map<String, String[]> appid2pn = new HashMap<String, String[]>();
	static {
		appid2pn.put("23044", pns);
	}

	public static String getPackageNameByAppId(String appid) {
		if (appid == null) {
			throw new NullPointerException();
		}
		String[] ps = appid2pn.get(appid);
		if (ps == null) {
			ps = new String[25];
			for (int i = 0; i < ps.length; i++) {
				ps[i] = getRandomPackageName();
			}
			appid2pn.put(appid, ps);
		}
		return ps[sRandom.nextInt(ps.length)];
	}

	public static String getRandomPackageName() {
		String result = "";
		int fst = sRandom.nextInt(5) + 1;
		int sec = sRandom.nextInt(5) + 1;
		int trd = sRandom.nextInt(5) + 1;
		for (int i = 0; i < fst; i++) {
			result += (char) ('a' + sRandom.nextInt(25));
		}
		result += ".";
		for (int i = 0; i < sec; i++) {
			result += (char) ('a' + sRandom.nextInt(25));
		}
		result += ".";
		for (int i = 0; i < trd; i++) {
			result += (char) ('a' + sRandom.nextInt(25));
		}
		return result;
	}

	public static String[] uas = new String[] { "GiONEE@GN9006",
			"samsung@SM-N9008S", "OPPO@R8207", "Flyme@MX3", "Flyme@MX4",
			"Hisense@M20-T", "OPPO@R7", "Lenovo@A590", "HUAWEI@ALE-CL00",
			"HUAWEI@GRA-TL00", "Coolpad@8675", "Lenovo@A680", "Lenovo@A7600-m",
			"Lenovo@A808t", "vivo@X5L", "ZTE@G719C", "HUAWEI@MT7-TL00",
			"HUAWEI@MT7-TL10", "HUAWEI@GRA-CL00", "HUAWEI@ALE-CL00",
			"Letv@X500", "vivo@Y18L" };

	public static String getRandomUA() {
		return uas[sRandom.nextInt(uas.length)];
	}

	public static String[] getAdIds(String resp) {
		if (resp == null) {
			return null;
		}
		int len = "\"adId\":\"".length();
		int index = resp.indexOf("\"adId\":\"");
		List<String> ids = new ArrayList<String>();
		while (index > 0) {
			resp = resp.substring(index + len);
			ids.add(resp.substring(0, 4));
			index = resp.indexOf("\"adId\":\"");
		}
		String[] results = new String[ids.size()];
		return ids.toArray(results);
	}

	public static String getProvince(String resp) {
		if (resp == null) {
			return null;
		}
		String anchor = "\"province\":\"";
		int len = anchor.length();
		int index = resp.indexOf(anchor);
		if (index > 0) {
			return resp.substring(index + len, index + len + 2);
		}
		return "";
	}

	/**
	 * random result
	 * 
	 * @param rate
	 *            rate to be true.should between 0.0 and 1.0
	 * @return
	 */
	public static boolean random(float rate) {
		if (rate < 0 || rate > 1) {
			throw new IllegalArgumentException();
		}
		return sRandom.nextFloat() < rate;
	}

	public static void sleep(int time, int range) {
		try {
			Thread.sleep(time * 1000 + sRandom.nextInt(range * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
