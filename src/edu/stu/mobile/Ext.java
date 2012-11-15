package edu.stu.mobile;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Ext extends Activity {
	private ListView floorList;
	private String[] floor = {
			"設計大樓", "行政大樓", "管理大樓", "圖資大樓", "不拘"
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ext);
		findViews();
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
			return newsData.size();
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return newsData.size();
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			mLayoutInflater = (LayoutInflater) News.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (!(newsData != null && newsData.size() > 0)) {
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

			((TextView) mInflater.findViewById(R.id.title)).setText(newsData.get(position).get("title"));
			mInflater.findViewById(R.id.arror).setBackgroundResource(arrorImg);
			mInflater.findViewById(R.id.header).setBackgroundResource(headerImg);
			return mInflater;
		}
	}
}
