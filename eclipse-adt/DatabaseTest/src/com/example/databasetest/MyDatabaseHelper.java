package com.example.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	/** 创建book表. */
	public static final String CREATE_BOOK = "create table book("
			+ "id integer primary key autoincrement," + "author text,"
			+ "price real," + "pages integer," + "name text,"+ "category_id integer)";
	
	/** 创建category表. */
	public static final String CREATE_CATEGORY = "create table category ("
			+ "id integer primary key autoincrement, " + "category_name text, "
			+ "category_code integer)";
	
	private Context mContext;

	public MyDatabaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BOOK);
		db.execSQL(CREATE_CATEGORY);
		Toast.makeText(mContext, "数据库创建完成", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*	删除所有数据 数据丢失;
			db.execSQL("drop table if exists book");
			db.execSQL("drop table if exists category");
			onCreate(db);
		*/
		
		// 没有break 实现跨版本升级时数据库也保持一致
		switch (oldVersion) {
		case 1:
			db.execSQL(CREATE_CATEGORY);
		case 2:
			db.execSQL("alter table book add column category_id integer");
		default:
			break;
		}
	}

}
