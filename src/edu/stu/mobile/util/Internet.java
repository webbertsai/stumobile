package edu.stu.mobile.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Internet {
	private NetworkInfo info = null;

	public Internet(Context context) {
		ConnectivityManager CM = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		info = CM.getActiveNetworkInfo();
	}

	/**
	 * 目前網路是否可使用
	 * 
	 * @param context 必要參數
	 * @return true 為可以使用
	 */
	public boolean isAvailable(Context context) {
		if (info == null) {
			return false;
		}
		return info.isAvailable();
	}

	/**
	 * 網路是否已連接
	 * 
	 * @param context 必要參數
	 * @return true 為可以使用
	 */
	public boolean isConnected(Context context) {
		if (info == null) {
			return false;
		}
		return info.isConnected();
	}

	/**
	 * 網路是否已連接或連線中
	 * 
	 * @param context 必要參數
	 * @return true 為可以使用
	 */
	public boolean isConnectedOrConnecting(Context context) {
		if (info == null) {
			return false;
		}
		return info.isConnectedOrConnecting();
	}
	
	public boolean isFailover(Context contxt) {
		if (info == null) {
			return false;
		}
		return info.isFailover();
	}
}
