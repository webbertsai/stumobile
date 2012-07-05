package edu.stu;

import android.app.Activity;
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
import android.widget.Toast;

public class StumobileActivity extends Activity {
	LinearLayout main;
	int iconWidth, iconHeigth, num = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		Matrix matrix = new Matrix();
		matrix.postScale(0.5f, 0.5f);
		// matrix.postRotate(45);
		Bitmap dstbmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
		ImageView IV = new ImageView(this);
		IV.setImageBitmap(dstbmp);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(80, 400, 0, 0);
		IV.setLayoutParams(params);
		IV.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(StumobileActivity.this, "hello", 0).show();
			}
		});
		main.addView(IV);

	}

	public void addView(LinearLayout.LayoutParams params, Bitmap bmp, int seatX, int seatY, ImageView.OnClickListener onclick) {

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