package com.example.bestbroadcastpratice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends BaseActivity {
	private ForceOffLineReceiver forceOffLineReceiver = new ForceOffLineReceiver();
	private IntentFilter intentFilter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((Button) findViewById(R.id.button1))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent("com.example.broadcastbestpractice. FORCE_OFFLINE");
						sendBroadcast(intent);
					}
				});

		// 注册广播
		intentFilter = new IntentFilter();
		intentFilter.addAction("com.example.broadcastbestpractice. FORCE_OFFLINE");
		registerReceiver(forceOffLineReceiver, intentFilter);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class ForceOffLineReceiver extends BroadcastReceiver {

		public void onReceive(final Context context, Intent intent) {
        	AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context); 
        	dialogBuilder.setTitle("警告"); 
        	dialogBuilder.setMessage("您的帐号在其他地方登陆,您被强制下线,请重新登陆."); 
        	dialogBuilder.setCancelable(false); 
        	dialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
        		
        	@Override
        	public void onClick(DialogInterface dialog, int which) { 
        		ActivityController.finishAll(); // 销毁所有活动 
        		Intent intent = new Intent(context, LoginActivity.class); 
        		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        		context.startActivity(intent); // 重新启动LoginActivity 
        	} 
        }); 
        	AlertDialog alertDialog = dialogBuilder.create(); 
        	// 需要设置AlertDialog的类型，保证在广播接收器中可以正常弹出 
        	alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT); 
        	alertDialog.show(); 
       }
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(forceOffLineReceiver);
	}
}
