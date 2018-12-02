package com.example.servicebestpractice;

import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class LongRunningService extends Service {
	private Intent intent2;
	private PendingIntent pi;
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(LongRunningService.class.getSimpleName(), "onCreate");
		intent2 = new Intent(this, AlarmReceiver.class);
		pi = PendingIntent.getBroadcast(this, 0, intent2, 0);
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Log.i(LongRunningService.class.getSimpleName(), "任务启动:"+new Date());
			}
		}).start();
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		long triggerAtMillis = SystemClock.elapsedRealtime()+10*1000;
		alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtMillis , pi );
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(LongRunningService.class.getSimpleName(), "onDestroy");
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(pi);
	}
}
