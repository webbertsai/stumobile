package edu.stu.mobile.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CacheHelper extends SQLiteOpenHelper{

	public CacheHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public void onCreate(SQLiteDatabase db) {
		
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
