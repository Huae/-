package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * ��̬�㲥������
 * ����ϵͳ����
 *		--> android.intent.action.BOOT_COMPLETED	
 */
public class BootCompleteReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "ϵͳ������ɡ�����������", Toast.LENGTH_SHORT).show();
	}

}
