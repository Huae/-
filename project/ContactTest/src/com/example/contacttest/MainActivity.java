package com.example.contacttest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView contactListView;
	private ArrayAdapter<String> adapter;
	private List<String> contacts = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 获取控件实例
		contactListView = (ListView) findViewById(R.id.contact_list_view);
		// 初始化并绑定适配器
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, contacts);
		contactListView.setAdapter(adapter);
		// 加载数据 读取联系人
		readContacts();
	}

	private void readContacts() {
		Cursor cursor = null;
		try {
			cursor = getContentResolver().query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					null, null, null);
			if (cursor != null) {
				while (cursor.moveToNext()) {
					// 获取联系人姓名和电话
					String contactName = cursor
							.getString(cursor
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
					String contactPhone = cursor
							.getString(cursor
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					// 将数据添加到集合中
					contacts.add(contactName + "\n" + contactPhone);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(cursor != null){
				cursor.close();	// 释放资源
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
