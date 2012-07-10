package edu.stu.mobile.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class ResolutionUtils {
	/**
	 * 取得螢幕解析度的寬
	 * @param activity 初始化用
	 * @return 螢幕解析度的寬
	 * @author Webber
	 */
	public static double getWidthPixels(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
	/**
	 * 取得螢幕解析度的高
	 * @param activity 初始化用
	 * @return 螢幕解析度的高
	 * @author Webber	
	 */
	public static double getHeightPixels(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
}
