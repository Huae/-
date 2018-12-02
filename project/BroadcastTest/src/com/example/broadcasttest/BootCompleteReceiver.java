package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 静态广播接受器
 * 监听系统开机
 *		--> android.intent.action.BOOT_COMPLETED	
 */
public class BootCompleteReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "系统开机完成》。。。。。", Toast.LENGTH_SHORT).show();
	}

}
