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
		// setContentView(R.layout.new_students_main);
		// findViews();
		//
		// title.setText("請選擇要辦理的事項");

		// Bitmap icon = BitmapFactory.decodeResource(getResources(),
		// R.drawable.icon03);
		// final Bitmap bmp2 = BitmapFactory.decodeResource(getResources(),
		// R.drawable.administrative);
		//
		// DynamicImageView imag = new DynamicImageView(this);
		// imag.setIcon(icon, 0.3f, 0.3f, 0);
		// imag.setPosition(400, 700, 480, 800);
		// imag.setOnClickListener(new OnClickListener() {
		//
		// public void onClick(View v) {
		// Dialog dialog = new Dialog(NewStudentsMain.this);
		// dialog.setContentView(R.layout.dialog);
		// dialog.setTitle("行政大樓");
		// ((ImageView) dialog.findViewById(R.id.iv)).setImageBitmap(bmp2);
		// ((TextView) dialog.findViewById(R.id.content)).setText("行政大樓真的非常好玩");
		// dialog.show();
		// }
		// });
		//
		// main.addView(imag);
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
