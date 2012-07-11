package edu.stu.mobile.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DynamicImageView extends ImageView {
	private Matrix matrix = null;
	private Context context = null;
	private String Tag = "DynamicImageView";

	/**
	 * 初始化ImageView
	 * 
	 * @param context 初始化作用
	 * @author Webber
	 */
	public DynamicImageView(Context context) {
		super(context);
		DynamicImageView.this.context = context;
	}

	/**
	 * 設定圖片的位置，左上角為(0,0)，X越大越是往右邊移動，Y越大則往下移動
	 * 
	 * @param seatX icon_X
	 * @param seatY icon_Y
	 * @param HeightPixelsDevelop 開發者手機解析度(高)
	 * @param WidthPixelsDevelop 開發者手機解析度(寬)
	 */
	public void setPosition(int seatX, int seatY, int DevelopPixelsHeight, int DevelopPixelsWidth) {
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(
				(int) (ResolutionUtils.getPixelsWidth(this.context) / DevelopPixelsHeight * seatX),
				(int) (ResolutionUtils.getPixelsHeight(this.context) / DevelopPixelsWidth * seatY), 0, 0);
		DynamicImageView.this.setLayoutParams(params);
	}

	/**
	 * 縮放比例如果是 "1"，為圖片本身大小，數字越小圖片即越小。 旋轉如果是 "0"，即為不旋轉，正數為向右邊轉。
	 * 
	 * @param icon 所顯示之圖案
	 * @param ScaleHeight 縮放比例(高)
	 * @param ScaleWidth 縮放比例(寬)
	 * @param Rotate 旋轉
	 * @author webber
	 */
	public void setIcon(Bitmap icon, float ScaleHeight, float ScaleWidth, float Rotate) {
		if (matrix == null) {
			matrix = new Matrix();
		}
		matrix.postScale(ScaleHeight, ScaleWidth);
		matrix.postRotate(Rotate);
		Bitmap dstbmp = Bitmap.createBitmap(icon, 0, 0, icon.getWidth(), icon.getHeight(), matrix, true);
		DynamicImageView.this.setImageBitmap(dstbmp);
	}

}
