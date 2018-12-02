package com.example.bestbroadcastpratice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private CheckBox checkBox;
	private Button login;
	private EditText name;
	private EditText password;
	private SharedPreferences sharedPreferences;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		name = (EditText) findViewById(R.id.name);
		password = (EditText) findViewById(R.id.password);
		checkBox = (CheckBox) findViewById(R.id.checkBox1);
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		if (sharedPreferences.getBoolean("rememberMe", false)) {
			String username = sharedPreferences.getString("name", "");
			name.setText(username);
			name.setSelection(username.length());
			password.setText(sharedPreferences.getString("password", ""));
			checkBox.setChecked(true);
		}
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String username = name.getText().toString().trim();
				String userpass = password.getText().toString().trim();
				SharedPreferences.Editor edit = sharedPreferences.edit();
				if (("admin".equals(username)) && ("123456".equals(userpass))) {
					if (checkBox.isChecked()) {
						edit.putBoolean("rememberMe", true);
						edit.putString("name", username);
						edit.putString("password", userpass);
					} else {
						edit.clear();
					}
					edit.commit();
					Intent intent = new Intent(LoginActivity.this,
							MainActivity.class);
					startActivity(intent);
					finish();
					return;
				}
				AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
				builder.setTitle("提示");
				builder.setMessage("admin,123456");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				});
				AlertDialog create = builder.create();
				create.show();
			}
		});
	}
}
