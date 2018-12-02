package com.example.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.SlidingDrawer;

public class MyService extends Service {
	private static final String TAG = "MyService";

	/**
	 * 活动与服务进行通信的binder
	 * 
	 * @author huang
	 */
	class DownloadBinder extends Binder {
		public void begainDownload() {
			Log.i(TAG, "begainDownload");
		}

		public void finishDownload() {
			Log.i(TAG, "finishDownload");
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i(TAG, "onBind");
		return new DownloadBinder();
	}

	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		// 前台服务
		Notification notification = new Notification(R.drawable.ic_launcher, "前台服务启动...", System.currentTimeMillis());
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
		notification.setLatestEventInfo(this, "标题", "内容", pi);
		startForeground(1, notification);
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0;i<10;i++){
					//int m = 1/0;
					try {
						Log.i(TAG, "执行任务...");
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				stopSelf();	// 任务完成停止服务
			}
		}).start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy");
		super.onDestroy();
	}
}
