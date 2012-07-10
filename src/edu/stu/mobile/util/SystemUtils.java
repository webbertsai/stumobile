package edu.stu.mobile.util;

import android.app.Activity;
import android.util.DisplayMetrics;

public class SystemUtils {
	/**
	 * 取得解析度的寬
	 * */
	public static int getwidthPixels(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 取得解析度的高
	 * */
	public static int getheightPixels(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
}
