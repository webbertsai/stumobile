package edu.stu.mobile;

import java.util.ArrayList;
import java.util.HashMap;

import edu.stu.mobile.util.ResolutionUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Main extends Activity {

	TextView About;
	GridView FunctionMenu;
	LinearLayout PageNum;

	ArrayList<HashMap<String, Object>> FunctionMenuData;

	private void setFunctionMenuData() {

		// 各功能Icon
		int FunctionMenuIcon[] = {
				R.drawable.ext_table, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03, R.drawable.icon03
		};
		// 個功能名稱 （建議使用 Strings.xml 新增，以便多國語系使用）
		String FunctionMenuText[] = {
				getString(R.string.ext_table), "test", "test", "test", "test", "test", "test", "test", "test"
		};

		// icon 與 title 相同數量才會新增
		if (FunctionMenuText.length == FunctionMenuIcon.length) {
			for (int i = 0; i < 8; i++) {
				HashMap<String, Object> index = new HashMap<String, Object>();
				index.put("title", FunctionMenuText[i]);
				index.put("icon", FunctionMenuIcon[i]);
				FunctionMenuData.add(index);
			}
		}

		for (int i = 0; i < (FunctionMenuIcon.length - 1) / 8 + 1; i++) {
			View dot = new View(this);
			dot.setBackgroundResource(R.drawable.dot_black);
			LayoutParams lp = new LayoutParams(10, 10);
			lp.setMargins(5, 0, 5, 0);
			dot.setLayoutParams(lp);
			PageNum.addView(dot);
		}

	};

	private void findViews() {
		About = (TextView) findViewById(R.id.About);
		FunctionMenu = (GridView) findViewById(R.id.FunctionMenu);
		PageNum = (LinearLayout) findViewById(R.id.FunctionMenuNumber);
	}

	private void init() {
		About.setText(getString(R.string.stu_name) + " | " + getString(R.string.stu_zipcode) + getString(R.string.stu_address) + " | TEL:" + getString(R.string.stu_phnoe));
		FunctionMenuData = new ArrayList<HashMap<String, Object>>();
		setFunctionMenuData();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
		init();
		FunctionMenu.setAdapter(new MainItem(this, FunctionMenuData, ResolutionUtils.getPixelsHeight(this) / 800, ResolutionUtils.getPixelsHeight(this) / 480));
	}

}
