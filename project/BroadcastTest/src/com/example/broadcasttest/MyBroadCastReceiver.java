package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * �����Զ���㲥��
 * @author huang
 *
 */
public class MyBroadCastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "received in BroadcastTest", Toast.LENGTH_SHORT).show();
	}

}
