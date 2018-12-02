package com.example.servicetest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.servicetest.MyService.DownloadBinder;

public class MainActivity extends Activity implements OnClickListener{
	/** 按钮声明.*/
	private Button startService;
	private Button stopService;
	private Button bindService;
	private Button unbindService;
	private Button intentService;
	// 绑定服务相关
	private MyService.DownloadBinder downloadBinder;
	
	/**
	 * 此类用于activity与service之间的通信(控制)
	 */
	private ServiceConnection connection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i(MainActivity.class.getSimpleName(),"onServiceDisconnected");
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i(MainActivity.this.getClass().getSimpleName(),"onServiceConnected");
			downloadBinder = (DownloadBinder) service;
			downloadBinder.begainDownload();
			downloadBinder.finishDownload();
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        intentService = (Button) findViewById(R.id.intent_service);
        
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        intentService.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.start_service:
			// 开启服务
			Intent intent = new Intent(this,MyService.class);
			startService(intent);
			break;
		case R.id.stop_service:
			Intent intent2 = new Intent(this,MyService.class);
			stopService(intent2);
			break;
		case R.id.bind_service:
			Intent intent3 = new Intent(this,MyService.class);
			bindService(intent3, connection, BIND_AUTO_CREATE);
			break;
		case R.id.unbind_service:
			unbindService(connection);
			break;
		case R.id.intent_service:
			// IntentService测试
			Log.i(MainActivity.class.getSimpleName(),"当前线程id:"+Thread.currentThread().getId());
			Intent intent5 = new Intent(this, MyIntentService.class);
			startService(intent5);
			break;

		default:
			break;
		}
	}
    
}
