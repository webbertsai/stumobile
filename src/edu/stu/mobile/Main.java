package edu.stu.mobile;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ViewFlipper;
import edu.stu.mobile.ui.ViewPagerAdapter;
import edu.stu.mobile.util.ResolutionUtils;

public class Main extends Activity implements OnPageChangeListener {

	TextView About;
	ViewPager FunctionMenu;
	ViewFlipper Panel;
	LinearLayout PageNum;
	String tag = "Main";
	ArrayList<View> FunctionPage;
	String PanelUrls[] = {
			"http://freshman.stu.edu.tw/", "http://ccds2012.stu.edu.tw/"
	};
	private void initPanel() {
		int PanelImages[] = {
				R.drawable.a1, R.drawable.a2
		};



		Panel.setInAnimation(this, R.anim.in_leftright);
		Panel.setOutAnimation(this, R.anim.out_leftright);
		Panel.setOnTouchListener(new OnTouchListener() {
			private boolean isMove = false;
			private double x, y;

			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				
				case MotionEvent.ACTION_DOWN:
					this.x = event.getX();
					this.y = event.getY();
					break;

				case MotionEvent.ACTION_UP:
					double Judgment = x - event.getX();
					if (Math.abs(Judgment) < 50 && Math.abs(this.y - event.getY()) < 50) {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PanelUrls[Panel.getDisplayedChild()])));
						break;
					}
					// 是否要自動換頁
					Panel.setAutoStart(false);

					// 由右向左
					if (Judgment > 50 && isMove) {
						Panel.setInAnimation(Main.this, R.anim.in_rightleft);
						Panel.setOutAnimation(Main.this, R.anim.out_rightleft);
						Panel.showNext();
					}

					// 由左向右
					if (Judgment < -50 && isMove) {
						Panel.setInAnimation(Main.this, R.anim.in_leftright);
						Panel.setOutAnimation(Main.this, R.anim.out_leftright);
						Panel.showPrevious();
					}

					Panel.getInAnimation().setAnimationListener(new AnimationListener() {

						public void onAnimationEnd(Animation arg0) {
							isMove = true;
						}

						public void onAnimationRepeat(Animation arg0) {

						}

						public void onAnimationStart(Animation arg0) {
							isMove = false;
						}

					});

					break;

				}

				return true;
			}
		});
		setPanelData(PanelImages, PanelUrls);
	}

	private void setPanelData(int[] PaneImages, String[] Urls) {
		if (PaneImages.length == Urls.length) {
			for (int index = 0; index < PaneImages.length; index++) {
				Panel.addView(newPanelView(PaneImages[index], Urls[index]));
			}
		}
	}

	private View newPanelView(int PaneImage, final String Url) {
		View v = new View(this);
		v.setBackgroundResource(PaneImage);
		return v;
	}

	/**
	 * 初始話所需要使用的 icon 及 title，如需要請在此增加（ 兩者數量需一致 ）
	 */
	private void initFunctionMenuitem() {
		// 各功能Icon
		int FunctionMenuIcon[] = {
				R.drawable.ext_table, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.ext_table, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03,
				R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03,
		};

		// 個功能名稱 （建議使用 Strings.xml 新增，以便多國語系使用）
		String FunctionMenuText[] = {
				getString(R.string.ext_table), "test", "test", "test", "test", "test", "test", "test", "test", getString(R.string.ext_table), "test", "test", "test", "test", "test", "test", "test", "test",
		};

		setFunctionMenuData(FunctionMenuIcon, FunctionMenuText);
	}

	/**
	 * 新增下方頁面頁碼
	 * 
	 * @param number
	 *            此點為第幾頁
	 * @param color
	 *            此點的顏色為何：1.藍色 2.黑色
	 */
	private void addFunctionMenuPagDot(int number, int color) {
		View dot = new View(this);
		if (color == 1) {
			dot.setBackgroundResource(R.drawable.dot_blue);
		} else {
			dot.setBackgroundResource(R.drawable.dot_black);
		}
		LayoutParams lp = new LayoutParams(10, 10);
		lp.setMargins(5, 0, 5, 0);
		dot.setLayoutParams(lp);
		PageNum.addView(dot, number);
	}

	private View newBtnPage(ArrayList<HashMap<String, Object>> FunctionMenuData) {
		GridView page = new GridView(this);
		// 一列最多幾個項目
		page.setNumColumns(4);
		// 把滑動的bar隱藏
		page.setScrollBarStyle(0);
		// 每列之間的高度兼具
		page.setVerticalSpacing(10);
		// 讓Gridview 不可以滑動
		page.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					return true;
				}
				return false;
			}

		});

		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		page.setLayoutParams(lp);
		page.setAdapter(new MainItem(this, FunctionMenuData, ResolutionUtils.getPixelsHeight(this) / 800, ResolutionUtils.getPixelsWidth(this) / 480));
		// gridview.setOnItemClickListener(clickFuncItem());

		return page;
	}

	private void setFunctionMenuData(int[] FunctionMenuIcon, String[] FunctionMenuText) {

		// icon 與 title 相同數量才會新增
		if (FunctionMenuText.length != FunctionMenuIcon.length) {
			Log.e(tag, "功能頁面 icon 與 title 數量不同");
			return;
		}

		for (int FunctionMenuPageDot = 1, PageDataMin = 0; FunctionMenuPageDot < (FunctionMenuIcon.length - 1) / 8 + 2; FunctionMenuPageDot++, PageDataMin += 8) {
			int PageDataMax = FunctionMenuPageDot * 8;
			if (FunctionMenuPageDot * 8 > FunctionMenuIcon.length) {
				PageDataMax = FunctionMenuIcon.length;
			}
			ArrayList<HashMap<String, Object>> FunctionMenuData = new ArrayList<HashMap<String, Object>>();
			for (int FunctionMenuPageData = PageDataMin; FunctionMenuPageData < PageDataMax; FunctionMenuPageData++) {
				HashMap<String, Object> index = new HashMap<String, Object>();
				index.put("title", FunctionMenuText[FunctionMenuPageData]);
				index.put("icon", FunctionMenuIcon[FunctionMenuPageData]);
				FunctionMenuData.add(index);
			}

			FunctionPage.add(newBtnPage(FunctionMenuData));
			if (FunctionMenuPageDot == 1) {
				addFunctionMenuPagDot(0, 1);
			} else {
				addFunctionMenuPagDot(1, 2);
			}
		}

		FunctionMenu.setAdapter(new ViewPagerAdapter(FunctionPage));
		FunctionMenu.setCurrentItem(0);
		FunctionMenu.setOnPageChangeListener(this);

	};

	private void findViews() {
		About = (TextView) findViewById(R.id.About);
		FunctionMenu = (ViewPager) findViewById(R.id.FunctionMenu);
		Panel = (ViewFlipper) findViewById(R.id.Panel);
		PageNum = (LinearLayout) findViewById(R.id.FunctionMenuNumber);
	}

	private void init() {
		About.setText(getString(R.string.stu_name) + " | " + getString(R.string.stu_zipcode) + getString(R.string.stu_address) + " | TEL:" + getString(R.string.stu_phnoe));
		FunctionPage = new ArrayList<View>();
		initFunctionMenuitem();
		initPanel();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
		init();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	int CurrentlyPages = 0;

	@Override
	public void onPageSelected(int arg0) {
		PageNum.removeViewAt(CurrentlyPages);
		CurrentlyPages = arg0;
		addFunctionMenuPagDot(arg0, 1);
	}

}