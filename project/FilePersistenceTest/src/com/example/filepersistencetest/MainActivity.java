package com.example.filepersistencetest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText editText;
	private Button button1;
	private Button button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (EditText) findViewById(R.id.editText1);
		String text = load();
		if (!TextUtils.isEmpty(text)) {
			editText.setText(text);
			editText.setSelection(text.length());
			Toast.makeText(this, "恢复完成", Toast.LENGTH_SHORT).show();
		}

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// SharedPreferences数据保存

				// 1.得到 SharedPreferences.Editor对象
				SharedPreferences.Editor edit = getSharedPreferences("data",
						MODE_PRIVATE).edit();
				// 2.使用editor对象添加数据
				edit.putString("name", "jack");
				edit.putInt("age", 20);
				edit.putBoolean("married", false);
				// 3.提交
				edit.commit();
			}
		});
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// SharedPreferences数据读取
				SharedPreferences sharedPreferences = getSharedPreferences(
						"data", MODE_PRIVATE);
				String name = sharedPreferences.getString("name", "wu");
				int age = sharedPreferences.getInt("age", 0);
				boolean married = sharedPreferences.getBoolean("married", false);
				float sds = sharedPreferences.getFloat("sds", 0.0f);
				
				new AlertDialog.Builder(MainActivity.this).setTitle("读取数据").setMessage("name:"+name+"\n"+"age:"+age+"\n"+"married:"+married+"\n"+"sds:"+sds).setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).show();
			}
		});

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		save(editText.getText().toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * 保存文本
	 */
	private void save(String text) {
		FileOutputStream out = null;
		BufferedWriter writer = null;
		try {
			out = openFileOutput("data", Context.MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write(text);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 读取文本
	 */
	private String load() {
		FileInputStream in = null;
		BufferedReader reader = null;
		StringBuilder content = new StringBuilder();
		try {
			in = openFileInput("data");
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
		} catch (Exception e) {
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return content.toString();
	}
}
