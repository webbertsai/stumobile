package edu.stu.mobile;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import edu.stu.mobile.db.CacheHelper;

public class StumobileActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.index);
		CacheHelper help = new CacheHelper(this);
//		SQLiteDatabase db = help.getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put("building_code", "A");
//		values.put("building_name", "行政大樓");
//		values.put("building_description", "SSSSS");
//		values.put("building_lat", 20.5);
//		values.put("building_lng", 30.5);
//		db.insertOrThrow("building", null, values);
//
//		Cursor cursor = db.query("building", new String[] { "building_code", "building_name", "building_description" }, null, null, null, null, null);
//		if (cursor.getCount() > 0) {
//			System.out.println("有資料");
//			cursor.moveToNext();
//			System.out.println(cursor.getString(0)); 
//		}
	}

	public void NewStu(View v) {
		startActivity(new Intent(StumobileActivity.this, edu.stu.mobile.FreshmanToApplicationListview.class));
	}

}