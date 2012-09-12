package edu.stu.mobile;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.support.v4.app.NotificationCompat.Action;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioGroup.LayoutParams;
import android.widget.TextView;

public class MainItem extends BaseAdapter {
	private LayoutInflater inflater;
	private List<HashMap<String, Object>> data;
	private double errorW, errorH;

	MainItem(Context context, List<HashMap<String, Object>> data, double errorW, double errorH) {
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.data = data;
		this.errorH = errorH;
		this.errorW = errorW;
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return data.size();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = inflater.inflate(R.layout.main_item, null);
		if (data != null && data.size() > 0) {
			View icon = view.findViewById(R.id.icon);
			icon.setBackgroundResource((Integer) data.get(position).get("icon"));
			icon.setLayoutParams(new LayoutParams((int) (this.errorH * 75), (int) (this.errorW * 75)));
			((TextView) view.findViewById(R.id.title)).setText((String) data.get(position).get("title"));
			final View down = view.findViewById(R.id.down);
			view.setOnTouchListener(new OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					//Log.w("TouchEvent",""+v.getId()+"==="+event.getAction());
					if (event.getAction() == MotionEvent.ACTION_DOWN) {
						down.findViewById(R.id.down).setVisibility(View.VISIBLE);
						return false;
					} else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL){
						down.findViewById(R.id.down).setVisibility(View.GONE);
					}
					return true;
				}
			});

		}

		return view;
	}

}
