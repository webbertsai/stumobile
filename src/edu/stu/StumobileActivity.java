package edu.stu;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class StumobileActivity extends Activity {
	LinearLayout main;
	int iconWidth, iconHeigth, num = 0;
	private ArrayList<HashMap<String, Object>> mData = new ArrayList<HashMap<String, Object>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

		main.addView(addView(bmp, 80, 400, new OnClickListener() {
			public void onClick(View v) {
				// startActivity(new Intent(StumobileActivity.this, aaa.class));
				final Dialog myDialog = new Dialog(StumobileActivity.this, R.style.dialog);
				myDialog.setContentView(R.layout.dialog);
				myDialog.show();
				

			}
		}));
	}

	private void setData() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		float scaleW = getwidthPixels() / 480.0f;
		float scaleH = getheightPixels() / 800.0f;
		data.put("scaleW", scaleW);
		data.put("scaleH", scaleH);
		data.put("bmp", bmp);
		data.put("OnClickListener", new OnClickListener() {
			public void onClick(View v) {
				final Dialog myDialog = new Dialog(StumobileActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
				myDialog.setContentView(R.layout.dialog);
				myDialog.show();
			}
		});
		mData.add(data);

	}

	public View addView(Bitmap bmp, int seatX, int seatY, ImageView.OnClickListener onclick) {
		Matrix matrix = new Matrix();
		// icon 縮放比例
		matrix.postScale(0.5f, 0.5f);
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
		IV.setOnClickListener(onclick);
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

	/**
	 * 取得解析度的寬
	 * */
	public int getwidthPixels() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 取得解析度的高
	 * */
	public int getheightPixels() {
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	private ImageView.OnClickListener IVOCL = new ImageView.OnClickListener() {
		public void onClick(View v) {
		}
	};

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