package com.example.notificationtest;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

public class AnotherActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_another);
		//  取消通知显示
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(1);
	}
}
