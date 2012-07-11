package edu.stu.mobile;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FreshmanToApplicationListview extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.freshman_to_application);
		findViews();
		title.setText(getString(R.string.FreshmanToApplication));
		application.setAdapter(new ApplicationBaseadapter(this));

	}

	ListView application = null;
	TextView title = null;

	private void findViews() {
		application = (ListView) findViewById(R.id.application);
		title = (TextView) findViewById(R.id.title);
	}

	class ApplicationBaseadapter extends BaseAdapter {
		private LayoutInflater mLayoutInflater;
		private View mInflater;
		private String[] key;
		private ArrayList<HashMap<String, String>> mData = new ArrayList<HashMap<String, String>>();

		ApplicationBaseadapter(Context context) {
			mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		private String getItem(int index, String key) {
			return mData.get(index).get(key);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

				mInflater = mLayoutInflater.inflate(R.layout.freshman_to_application_baseadpter, null);


			return mInflater;
		}
	}

}
