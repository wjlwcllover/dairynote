package com.dairybook.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DairyHelper extends SQLiteOpenHelper {

	private final static String CREATE_DAIRY = "create table dairy ("
			+ "id integer  primary key   autoincrement,"
			+ "image_id integer,"
			+ "dairy_title text," 
			+ "dairy_content text,"
			+ "dairy_date text)";

	public DairyHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_DAIRY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 

	}

}
