package com.example.providertest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private String newId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		btn4 = (Button) findViewById(R.id.button4);

		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 添加
				Uri uri = Uri
						.parse("content://com.example.databasetest.provider/book");
				ContentValues values = new ContentValues();
				values.put("name", "yibenshu");
				values.put("author", "zuozhe");
				values.put("pages", 200);
				//values.put("price", 300);
				values.put("price", 55.55); // 测试git
				Uri insert = getContentResolver().insert(uri, values);
				newId = insert.getPathSegments().get(1);
			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 修改
				ContentValues values = new ContentValues();
				values.put("name", "yibenshu1");
				values.put("author", "zuozhe1");
				values.put("pages", 2001);
				values.put("price", 3001);

				/*
				 * 自己添加条件 Uri uri =
				 * Uri.parse("content://com.example.databasetest.provider/book"
				 * ); contentResolver.update(uri, values , "id = ?", new
				 * String[]{newId});
				 */
				// 使用默认条件 根据id
				Uri uri = Uri
						.parse("content://com.example.databasetest.provider/book/"
								+ newId);
				getContentResolver().update(uri, values, null, null);
			}
		});
		btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 查询
				Uri uri = Uri
						.parse("content://com.example.databasetest.provider/book");
				Cursor cursor = getContentResolver().query(uri, null, null, null,
						null);
				if (cursor != null) {
					while (cursor.moveToNext()) {
						String name = cursor.getString(cursor
								.getColumnIndex("name"));
						String author = cursor.getString(cursor
								.getColumnIndex("author"));
						int pages = cursor.getInt(cursor
								.getColumnIndex("pages"));
						double price = cursor.getDouble(cursor
								.getColumnIndex("price"));
						String result = "name:" + name + "\n" + "author:"
								+ author + "\n" + "pages:" + pages + "\n"
								+ "price:" + price;
						Toast.makeText(MainActivity.this, result,
								Toast.LENGTH_SHORT).show();
					}
				}
				cursor.close();
			}
		});
		btn4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 删除
				Uri uri = Uri
						.parse("content://com.example.databasetest.provider/book/"
								+ newId);
				getContentResolver().delete(uri, null, null);
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
