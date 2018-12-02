package com.example.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	// 自定义广播相关
	private IntentFilter intentFilter;
	private NetworkChangeReceiver networkChangeReceiver;
	
	// 本地广播相关
	private LocalReceiver localReceiver;
	private IntentFilter intentFilter2;
	private LocalBroadcastManager localBroadcastManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		intentFilter = new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE"); // 注册需要监听的广播

		networkChangeReceiver = new NetworkChangeReceiver();
		// 动态注册广播接收器
		registerReceiver(networkChangeReceiver, intentFilter);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 发送自定义广播(标准广播)
				Intent intent = new Intent(
						"com.example.broadcasttest.MY_BROADCAST_RECEIVER");
				// sendBroadcast(intent);
				// 发送有序广播 需在清单文件中配置优先级，优先级高的先接受广播 且可终止广播的继续传播
				sendOrderedBroadcast(intent, null);
			}
		});

		localBroadcastManager = LocalBroadcastManager.getInstance(this);
		
		((Button) findViewById(R.id.button2))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// 发送本地广播
						Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
						localBroadcastManager.sendBroadcast(intent);
					}
				});
		// 注册本地广播
		intentFilter2 = new IntentFilter();
		localReceiver = new LocalReceiver();
		intentFilter2.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
		localBroadcastManager.registerReceiver(localReceiver, intentFilter2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(networkChangeReceiver);
	}

	class NetworkChangeReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			/*
			 * Toast.makeText(context, "network changes", Toast.LENGTH_SHORT)
			 * .show();
			 */
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager
					.getActiveNetworkInfo();
			if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
				Toast.makeText(context, "network is available",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(context, "network is unavailable",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	/**
	 * 本地广播类
	 * 
	 * @author huang
	 * 
	 */
	class LocalReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context, "receive localbroadcast in broadcastest",
					Toast.LENGTH_SHORT).show();
		}

	}
}
