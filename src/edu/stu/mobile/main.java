package edu.stu.mobile;

import java.util.ArrayList;
import java.util.HashMap;

import ui.ViewPagerAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import edu.stu.mobile.util.ResolutionUtils;

public class Main extends Activity implements OnPageChangeListener {

	TextView About;
	ViewPager FunctionMenu;
	LinearLayout PageNum;
	String tag = "Main";

	ArrayList<View> FunctionPage;

	/**
	 * 初始話所需要使用的 icon 及 title，如需要請在此增加（ 兩者數量需一致 ）
	 */
	private void initFunctionMenuitem() {
		// 各功能Icon
		int FunctionMenuIcon[] = {
				R.drawable.ext_table, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03,
				R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03,
				R.drawable.icon03, R.drawable.ext_table, R.drawable.icon03, R.drawable.icon03, 
				R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03,
				R.drawable.icon03, R.drawable.icon03,
		};
		// 個功能名稱 （建議使用 Strings.xml 新增，以便多國語系使用）
		String FunctionMenuText[] = {
				getString(R.string.ext_table), "test", "test", "test", "test", "test",
				"test", "test", "test", getString(R.string.ext_table), "test", "test",
				"test", "test", "test", "test", "test", "test",
		};

		setFunctionMenuData(FunctionMenuIcon, FunctionMenuText);
	}

	private View newBtnPage(ArrayList<HashMap<String, Object>> FunctionMenuData) {
		GridView page = new GridView(this);
		// 一列最多幾個項目
		page.setNumColumns(4);
		page.setScrollBarStyle(0);
		page.setVerticalSpacing(10);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		page.setLayoutParams(lp);
		page.setAdapter(new MainItem(this, FunctionMenuData, ResolutionUtils.getPixelsHeight(this) / 800, ResolutionUtils.getPixelsWidth(this) / 480));
		
		// gridview.setOnTouchListener(forbidenScroll());
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

			View dot = new View(this);
			if (FunctionMenuPageDot == 1) {
				dot.setBackgroundResource(R.drawable.dot_blue);
			}
			dot.setBackgroundResource(R.drawable.dot_black);
			LayoutParams lp = new LayoutParams(10, 10);
			lp.setMargins(5, 0, 5, 0);
			dot.setLayoutParams(lp);
			PageNum.addView(dot);
		}

		FunctionMenu.setAdapter(new ViewPagerAdapter(FunctionPage));
		FunctionMenu.setCurrentItem(0);
		FunctionMenu.setOnPageChangeListener(this);

	};

	private void findViews() {
		About = (TextView) findViewById(R.id.About);
		FunctionMenu = (ViewPager) findViewById(R.id.FunctionMenu);
		PageNum = (LinearLayout) findViewById(R.id.FunctionMenuNumber);
	}

	private void init() {
		About.setText(getString(R.string.stu_name) + " | " + getString(R.string.stu_zipcode) + getString(R.string.stu_address) + " | TEL:" + getString(R.string.stu_phnoe));
		FunctionPage = new ArrayList<View>();
		initFunctionMenuitem();

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
		View dot = new View(this);
		dot.setBackgroundResource(R.drawable.dot_blue);
		LayoutParams lp = new LayoutParams(10, 10);
		lp.setMargins(5, 0, 5, 0);
		dot.setLayoutParams(lp);
		PageNum.addView(dot, arg0);
	}

}
