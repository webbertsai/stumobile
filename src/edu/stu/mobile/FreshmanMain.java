package edu.stu.mobile;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import edu.stu.mobile.util.ResolutionUtils;

public class FreshmanMain extends Activity {

	private TextView title = null;
	private RelativeLayout main = null;

	private ArrayList<HashMap<String, Object>> mData = new ArrayList<HashMap<String, Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private void findViews() {
		// main = (RelativeLayout) findViewById(R.id.main);
		title = (TextView) findViewById(R.id.title);

	}

	private void setData(int x, int y, Bitmap icon, Bitmap main, String content) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("scaleW", ResolutionUtils.getPixelsHeight(this) / 480 * x);
		data.put("scaleH", ResolutionUtils.getPixelsWidth(this) / 800 * y);
		data.put("bmp", icon);
		mData.add(data);
	}

	public void need(View a) {
		Toast.makeText(this, "ok", 0).show();
	}

}
