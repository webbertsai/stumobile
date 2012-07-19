package edu.stu.mobile.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CacheHelper extends SQLiteOpenHelper {

	String tag = "CacheHelper";
	
	/**
	 * Version constant to increment when the database should be rebuilt
	 */
	private static final int VERSION = 1;

	/**
	 * Name of database file
	 */
	private static final String NAME = "cache.sqlite";

	public CacheHelper(Context context) {
		super(context, NAME, null, VERSION);
		Log.i(tag, "CacheHelper()");
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
	    super.onOpen(db);
	    if (!db.isReadOnly()) {
	        // Enable foreign key constraints
	        db.execSQL("PRAGMA foreign_keys = ON;");
	    }
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(tag, "onCreate()");
		db.execSQL("CREATE TABLE building (" +
				"building_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
				"building_code VARCHAR NOT NULL," +
				"building_name VARCHAR NOT NULL," +
				"building_description TEXT NOT NULL," +
				"building_lat DOUBLE NOT NULL," +
				"building_lng DOUBLE NOT NULL )");
		
		db.execSQL("CREATE TABLE office (" +
				"office_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + 
				"office_name VARCHAR NOT NULL," +
				"office_ext INTEGER NOT NULL," +
				"office_lat DOUBLE NOT NULL," +
				"office_lng DOUBLE NOT NULL," +
				"building_id INTEGER NOT NULL, " +
				"FOREIGN KEY (building_id) REFERENCES building(building_id)  ON DELETE CASCADE )");
		
		db.execSQL("CREATE TABLE unit (" +
				"unit_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
				"unit_name VARCHAR NOT NULL," +
				"unit_ext INTEGER NOT NULL," +
				"office_id INTEGER NOUT NULL," +
				"FOREIGN KEY (office_id) REFERENCES office(office_id) ON DELETE CASCADE )");
		
	
		
		db.execSQL("CRATE TABLE application_form (" +
				"application_form_id INTEGER PRIMARY KEY AUTOINCREMNT NOT NULL," +
				"application_form_name VARCHAR NOT NULL," +
				"application_form_description TEXT NOT NULL," +
				"application_form_data_start VARCHAR NOT NULL," +
				"application_form_data_end VARCHAR NOT NULL," +
				"unit_id INTEGER NOT NULL," +
				"FOREIGN KEY (unit_id) REFERENCES unit(unit_id) ON DELETE CASCADE  )");
		
		db.execSQL("CRATE TABLE application_form_requirement (" +
				"application_form_requirement_id INTEGER PRIMARY KEY AUTOINCREMNT NOT NULL," +
				"application_form_requirement_item VARCHAR NOT NULL," +
				"application_form_requirement_url TEXT NOT NULL," +
				"application_form_id INTEGER NOT NULL," +
				"FOREIGN KEY (application_form_id) REFERENCES application_form(application_form_id) NO DELETE CASCADE )");
		
		
		db.execSQL("CRATE TABLE user_apply (" +
				"user_apply_id INTEGER PRIMARY KEY AUTOINCREMNT NOT NULL," +
				"application_form_id INTEGER NOT NULL," +
				"FOREING KEY (application_form_id) REFERENCES application_form(application_form_id) ON DELETE CASCADE )");
		
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(tag, "onUpgrade()");
		db.execSQL("DROP TABLE IF EXISTS building");
		db.execSQL("DROP TABLE IF EXISTS office");
		db.execSQL("DROP TABLE IF EXISTS unit");
		db.execSQL("DROP TABLE IF EXISTS application_form");
		db.execSQL("DROP TABLE IF EXISTS application_form_requirement");
		db.execSQL("DROP TABLE IF EXISTS user_apply");
	}

}
