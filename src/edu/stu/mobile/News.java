package edu.stu.mobile;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import edu.stu.mobile.util.Internet;
import edu.stu.mobile.util.ParseJson;

public class News extends Activity {
	private ListView listNews;
	List<HashMap<String, String>> newsData;
	private ProgressDialog lodeing;
	private String[] dataKey = { "id", "title", "start_date", "senderdept", "sendername", "senderext" };
	private static final int parseJsonEnd = 1;

	private boolean checkIntrent() {
		Internet internet = new Internet(this);
		if (!internet.checkInternet()) {
			new AlertDialog.Builder(this).setTitle("請選擇網路連線方式").setMessage("需要有網路連線才能夠讀取資料！！")
					.setPositiveButton("WIFI", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
						}
					}).setNegativeButton("3G", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
						}
					}).show();

			if (lodeing != null)
				lodeing.dismiss();
			return false;
		}

		if (!internet.isWanConnect("8.8.8.8")) {
			new AlertDialog.Builder(this).setTitle("尚未擁有對外連線能力或網路不穩定").setMessage("請問是否需要開啟瀏覽器進行帳網路號密碼驗證！！")
					.setPositiveButton("要", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.stu.edu.tw")));
						}
					}).setNegativeButton("不要", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
						}
					}).show();

			if (lodeing != null)
				lodeing.dismiss();
			return false;
		}

		return true;
	}

	private void findViews() {
		listNews = (ListView) findViewById(R.id.list_news);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news);
		findViews();
		lodeing = ProgressDialog.show(this, getString(R.string.Lodeing), getString(R.string.LodeingContext));

		listNews.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				String url = "http://www.stu.edu.tw/news/";
				String urlData = newsData.get(arg2).get("id");
				Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(url + urlData.substring(urlData.indexOf(":\"") + 2, urlData.length() - 2)
						+ ".html"));
				startActivity(browser);

			}

		});
		new Thread() {
			public void run() {
				if (checkIntrent()) {
					super.run();
					newsData = ParseJson.parseJson(ParseJson.getWebserviceJson("http://www.stu.edu.tw/news/this-month.json"), dataKey);
					Message msg = new Message();
					msg.what = parseJsonEnd;
					mHandler.sendMessage(msg);
				}

			}
		}.start();

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (checkIntrent()) {
			new Thread() {
				public void run() {

					super.run();
					newsData = ParseJson.parseJson(ParseJson.getWebserviceJson("http://www.stu.edu.tw/news/this-month.json"), dataKey);
					Message msg = new Message();
					msg.what = parseJsonEnd;
					mHandler.sendMessage(msg);

				}
			}.start();
		}
	};

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case parseJsonEnd:
				listNews.setAdapter(new NewsAdapter());
				if (lodeing != null && lodeing.isShowing()) {
					lodeing.dismiss();
				}
				break;
			}
		};
	};

	class NewsAdapter extends BaseAdapter {
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

	public void onBreak(View v) {
		System.out.println("aaaaaaaaaa");
		finish();
	}

}
