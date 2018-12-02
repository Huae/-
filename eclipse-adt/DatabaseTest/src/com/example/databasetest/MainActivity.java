package com.example.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
@SuppressWarnings("unused")
public class MainActivity extends Activity {
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button button10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		button6 = (Button) findViewById(R.id.button6);
		button7 = (Button) findViewById(R.id.button7);
		button8 = (Button) findViewById(R.id.button8);
		button9 = (Button) findViewById(R.id.button9);
		button10 = (Button) findViewById(R.id.button10);

		final MyDatabaseHelper databaseHelper = new MyDatabaseHelper(
				MainActivity.this, "BookStore.db", null, 2);
		((Button) findViewById(R.id.button1))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// 创建数据库
						SQLiteDatabase db = databaseHelper
								.getWritableDatabase();
					}
				});

		// 增加数据
		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SQLiteDatabase db = databaseHelper.getWritableDatabase();
				ContentValues values = new ContentValues();

				values.put("name", "Java学习指南");
				values.put("author", "java");
				values.put("pages", 500);
				values.put("price", 20.90);
				db.insert("book", null, values);

				values.clear();
				values.put("name", "安卓学习指南");
				values.put("author", "安卓");
				values.put("pages", 540);
				values.put("price", 23.90);
				db.insert("book", null, values);
			}
		});

		// 更新数据
		button3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SQLiteDatabase db = databaseHelper.getWritableDatabase();

				ContentValues values = new ContentValues();
				values.put("author", "android");
				db.update("book", values, "name = ?", new String[] { "安卓学习指南" });
			}
		});

		// 删除数据
		button4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SQLiteDatabase db = databaseHelper.getWritableDatabase();
				db.delete("book", "name = ?", new String[] { "Java学习指南" });
				Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT)
						.show();
			}
		});
		// 查询数据
		button5.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SQLiteDatabase db = databaseHelper.getWritableDatabase();
				// db.query(table, columns, selection, selectionArgs, groupBy,
				// having, orderBy);
				// 查询数据
				Cursor query = db.query("book", null, null, null, null, null,
						null);
				if (query.moveToFirst()) { // 移动游标到第一行，数据为空时返回false
					// 遍历数据
					do {
						String name = query.getString(query
								.getColumnIndex("name"));
						String author = query.getString(query
								.getColumnIndex("author"));
						int pages = query.getInt(query.getColumnIndex("pages"));
						double price = query.getDouble(query
								.getColumnIndex("price"));
						String result = "name:" + name + "\n" + "author:"
								+ author + "\n" + "pages:" + pages + "\n"
								+ "price:" + price;
						Toast.makeText(MainActivity.this, result,
								Toast.LENGTH_SHORT).show();
					} while (query.moveToNext());
				}
			}
		});

		// 原生SQL操作
		button6.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SQLiteDatabase db = databaseHelper.getWritableDatabase();
				db.execSQL(
						"insert into book(name,author,pages,price) values(?,?,?,?)",
						new String[] { "新华字典", "xxx", "999999", "9999" });
			}
		});
		button7.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SQLiteDatabase db = databaseHelper.getWritableDatabase();
				db.execSQL(
						"update book set name=?,author=?,price=? where name=?",
						new Object[] { "xinhuazidian", "xinhua", 100, "新华字典"});
			}
		});
		button8.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SQLiteDatabase db = databaseHelper.getWritableDatabase();
				db.execSQL("delete from book where name=?",
						new String[] { "xinhuazidian" });
			}
		});
		button9.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				SQLiteDatabase db = databaseHelper.getWritableDatabase();
				Cursor query = db.rawQuery("select * from book where id < ?", new String[]{"4"});
				if (query.moveToFirst()) { // 移动游标到第一行，数据为空时返回false
					// 遍历数据
					do {
						String name = query.getString(query
								.getColumnIndex("name"));
						String author = query.getString(query
								.getColumnIndex("author"));
						int pages = query.getInt(query.getColumnIndex("pages"));
						double price = query.getDouble(query
								.getColumnIndex("price"));
						String result = "name:" + name + "\n" + "author:"
								+ author + "\n" + "pages:" + pages + "\n"
								+ "price:" + price;
						Toast.makeText(MainActivity.this, result,
								Toast.LENGTH_SHORT).show();
					} while (query.moveToNext());
				}
			}
		});
		
		// 事务操作
		button10.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = databaseHelper.getWritableDatabase();
				try{
					db.beginTransaction(); 	// 开启事务
					//db.execSQL("delete from book where 1 = 1");	// 删除数据
					db.execSQL("delete from book");
					//db.execSQL("TRUNCATE TABLE book"); 不行，没有该命令
					
					int i = 1/0;	// 模拟异常
					
					// 添加数据
					ContentValues values = new ContentValues();

					values.put("name", "Java学习指南");
					values.put("author", "java");
					values.put("pages", 500);
					values.put("price", 20.90);
					db.insert("book", null, values);

					values.clear();
					values.put("name", "安卓学习指南");
					values.put("author", "安卓");
					values.put("pages", 540);
					values.put("price", 23.90);
					db.insert("book", null, values);
					
					db.setTransactionSuccessful();	// 事务执行成功
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					db.endTransaction();	// 结束事务
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
