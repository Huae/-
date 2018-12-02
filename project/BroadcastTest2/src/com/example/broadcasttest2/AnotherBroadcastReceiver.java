package com.example.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AnotherBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, " received in another broadcastreceiver", Toast.LENGTH_SHORT).show();
		// 截断广播
		abortBroadcast();
		
		//Log.i("AnotherBroadcastReceiver", "截断广播");
	}

}
