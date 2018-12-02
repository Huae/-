package com.example.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
	public MyIntentService() {
		super("MyIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i(MyIntentService.class.getSimpleName(),"执行任务...");
		Log.i(MyIntentService.class.getSimpleName(),"当前线程id:"+Thread.currentThread().getId());
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(MyIntentService.class.getSimpleName(),"自动停止服务...");
	}
}
