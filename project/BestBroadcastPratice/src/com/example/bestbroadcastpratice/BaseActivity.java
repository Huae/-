package com.example.bestbroadcastpratice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("当前activity", getClass().getSimpleName());
		ActivityController.addActivity(this);
	}

	protected void onDestroy() {
		super.onDestroy();
		ActivityController.removeActivity(this);
	}
}
