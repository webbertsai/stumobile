package edu.stu.mobile;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

public class Ext extends Activity {
	private ListView floorList;
	String floor[] = { "行政大樓" };
	String group[] = { "教務處", "註冊組", "課務組", "綜合業務組" };
	int groupExt[] = { -1, 2005, 2018, 2008 };

	List<HashMap<String, String>> floorData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ext);
		findViews();
		floorList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				floorList.setAdapter(new GroupAdapter());
				floorList.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						if (groupExt[arg2] != -1) {
							Intent call = new Intent("android.intent.action.CALL", Uri.parse("tel:" + "076158000," + groupExt[arg2]));
							startActivity(call);
						}

					}
				});

			}
		});

		floorList.setAdapter(new FloorAdapter());
	}

	private void findViews() {
		floorList = (ListView) findViewById(R.id.floor_list);
	}

	class FloorAdapter extends BaseAdapter {
		private int arrorImg, headerImg;
		private LayoutInflater mLayoutInflater;
		private View mInflater = null;

		public int getCount() {
			return floor.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return floor.length;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			mLayoutInflater = (LayoutInflater) Ext.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (floor == null || floor.length < 0) {
				return null;
			}
			mInflater = mLayoutInflater.inflate(R.layout.news_item, null);

			if (position % 2 == 0) {
				arrorImg = R.drawable.list_arror_green;
				headerImg = R.drawable.list_header_green;
			} else {
				arrorImg = R.drawable.list_arror_blue;
				headerImg = R.drawable.list_header_blue;
			}

			TextView title = ((TextView) mInflater.findViewById(R.id.title));
			title.setText(floor[position]);
			LayoutParams layoutParams = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL);
			title.setLayoutParams(layoutParams);
			mInflater.findViewById(R.id.arror).setBackgroundResource(arrorImg);
			mInflater.findViewById(R.id.header).setBackgroundResource(headerImg);
			return mInflater;
		}
	}

	class GroupAdapter extends BaseAdapter {
		private int arrorImg, headerImg;
		private LayoutInflater mLayoutInflater;
		private View mInflater = null;

		public int getCount() {
			return group.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return group.length;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			mLayoutInflater = (LayoutInflater) Ext.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (floor == null || floor.length < 0) {
				return null;
			}
			mInflater = mLayoutInflater.inflate(R.layout.news_item, null);

			if (position % 2 == 0) {
				arrorImg = R.drawable.list_arror_green;
				headerImg = R.drawable.list_header_green;
			} else {
				arrorImg = R.drawable.list_arror_blue;
				headerImg = R.drawable.list_header_blue;
			}

			TextView title = ((TextView) mInflater.findViewById(R.id.title));
			title.setText(group[position]);
			LayoutParams layoutParams = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL);
			title.setLayoutParams(layoutParams);
			mInflater.findViewById(R.id.arror).setBackgroundResource(arrorImg);
			mInflater.findViewById(R.id.header).setBackgroundResource(headerImg);
			return mInflater;
		}
	}

	// 按鈕監聽事件
	public void movePage(View v) {
		Class Page = null;
		switch (v.getId()) {
		case R.id.news:
			Page = News.class;
			break;
		}

		if (Page != null) {

			Intent PageIntent = new Intent();
			PageIntent.setClass(this, Page);
			PageIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(PageIntent);
		}
	}

	public void onBreak(View v) {
		finish();
	}
}
