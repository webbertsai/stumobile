package edu.stu.mobile;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import edu.stu.mobile.util.SystemUtils;

public class NewStudentsMain extends Activity {

	LinearLayout main;
	int iconWidth, iconHeigth, num = 0;
	private ArrayList<HashMap<String, Object>> mData = new ArrayList<HashMap<String, Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_students_main);
		findViews();

		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon03);
		Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.administrative);

		setData(80, 400, bmp, bmp2, "行政大樓");
		addView((Bitmap) mData.get(0).get("bmp"), (Integer) mData.get(0).get("scaleW"), (Integer) mData.get(0).get("scaleH"), bmp2, "行政大樓",
				"處理行政一起的地方");

		main.addView(addView(bmp, 80, 400, bmp2, "行政大樓", "所有行政相關單位通通匯集於此"));
	}

	private void setData(int x, int y, Bitmap icon, Bitmap main, String content) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("scaleW", SystemUtils.getheightPixels(this) / 480 * x);
		data.put("scaleH", SystemUtils.getwidthPixels(this) / 800 * y);
		data.put("bmp", icon);
		mData.add(data);
	}

	public View addView(Bitmap bmp, int seatX, int seatY, final Bitmap bmp2, final String title, final String data) {
		Matrix matrix = new Matrix();
		// icon 縮放比例
		matrix.postScale(0.3f, 0.3f);
		// icon 旋轉
		// matrix.postRotate(45);
		// 將bmp進行縮放
		Bitmap dstbmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
		ImageView IV = new ImageView(this);
		IV.setImageBitmap(dstbmp);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		// 設定打點位置
		params.setMargins(seatX, seatY, 0, 0);
		IV.setLayoutParams(params);
		// 設定處發事件
		IV.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// startActivity(new Intent(StumobileActivity.this, aaa.class));
				final Dialog myDialog = new Dialog(NewStudentsMain.this, R.style.dialog);
				myDialog.setTitle(title);
				myDialog.setContentView(R.layout.dialog);
				((ImageView) myDialog.findViewById(R.id.iv)).setImageBitmap(bmp2);
				((TextView) myDialog.findViewById(R.id.content)).setText(data);
				myDialog.show();

			}
		});
		return IV;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		// 按下
		case MotionEvent.ACTION_DOWN:
			// 拖曳
		case MotionEvent.ACTION_MOVE:
			break;
		}
		return true;
	}

	private void findViews() {
		main = (LinearLayout) findViewById(R.id.main);
	}

	
	

	
	

	class Panel extends View {
		float hscale;
		float wscale;

		public Panel(Context context, int width, int height) {
			super(context);
			this.wscale = width / 480.0f;
			this.hscale = height / 800.0f;
		}

		@Override
		public void onDraw(Canvas canvas) {
			Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
			Matrix matrix = new Matrix();
			matrix.postScale(0.5f, 0.5f);
			// matrix.postRotate(45);
			Bitmap dstbmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
			canvas.drawBitmap(dstbmp, 80 * wscale, 400 * hscale, null);
		}

	}
}
