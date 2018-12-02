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
	// �Զ���㲥���
	private IntentFilter intentFilter;
	private NetworkChangeReceiver networkChangeReceiver;
	
	// ���ع㲥���
	private LocalReceiver localReceiver;
	private IntentFilter intentFilter2;
	private LocalBroadcastManager localBroadcastManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		intentFilter = new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE"); // ע����Ҫ�����Ĺ㲥

		networkChangeReceiver = new NetworkChangeReceiver();
		// ��̬ע��㲥������
		registerReceiver(networkChangeReceiver, intentFilter);

		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// �����Զ���㲥(��׼�㲥)
				Intent intent = new Intent(
						"com.example.broadcasttest.MY_BROADCAST_RECEIVER");
				// sendBroadcast(intent);
				// ��������㲥 �����嵥�ļ����������ȼ������ȼ��ߵ��Ƚ��ܹ㲥 �ҿ���ֹ�㲥�ļ�������
				sendOrderedBroadcast(intent, null);
			}
		});

		localBroadcastManager = LocalBroadcastManager.getInstance(this);
		
		((Button) findViewById(R.id.button2))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// ���ͱ��ع㲥
						Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
						localBroadcastManager.sendBroadcast(intent);
					}
				});
		// ע�᱾�ع㲥
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
	 * ���ع㲥��
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
