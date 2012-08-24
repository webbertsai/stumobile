package edu.stu.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Main_item extends BaseAdapter {
	private LayoutInflater inflater;
	private ArrayList<HashMap<String, Object>> data;

	Main_item(Context context, ArrayList<HashMap<String, Object>> data) {
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.data = data;
	}

	public int getCount() {
		return 0;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

}
