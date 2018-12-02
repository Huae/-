package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 发送自定义广播类
 * @author huang
 *
 */
public class MyBroadCastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "received in BroadcastTest", Toast.LENGTH_SHORT).show();
	}

}
