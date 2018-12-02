package com.example.smstest;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// 接受信息相关
	private TextView sender;
	private TextView content;
	private IntentFilter intentFilter;
	private MySmsReceiver smsReceiver;
	// 发送信息相关
	private EditText receiver;
	private EditText sendMsg;
	private Button send;
	private IntentFilter intentFilter2;
	private SendStatusReceiver sendStatusReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sender = (TextView) findViewById(R.id.sender);
		content = (TextView) findViewById(R.id.content);
		receiver = (EditText) findViewById(R.id.receiver);
		sendMsg = (EditText) findViewById(R.id.sendMsg);
		send = (Button) findViewById(R.id.send);
		// 发送消息
		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取短信管理器
				final SmsManager manager = SmsManager.getDefault();
				// 短信发送成功后发送的广播
				Intent intent = new Intent("SENT_SMS_ACTION");
				final PendingIntent pdIntent = PendingIntent.getBroadcast(
						MainActivity.this, 0, intent, 0);
				// 发送消息
				new Thread(new Runnable() {

					@Override
					public void run() {
						manager.sendTextMessage(receiver.getText().toString()
								.trim(), null, sendMsg.getText().toString()
								.trim(), pdIntent, null);
					}
				}).start();

			}
		});

		smsReceiver = new MySmsReceiver();
		intentFilter = new IntentFilter();
		intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED"); // android.provider.Telephony.SMS_RECEIVED是有序广播
		intentFilter.setPriority(100); // 优先级
		// 注册收到短信服务
		registerReceiver(smsReceiver, intentFilter);

		sendStatusReceiver = new SendStatusReceiver();
		intentFilter2 = new IntentFilter();
		intentFilter2.addAction("SENT_SMS_ACTION");
		// 注册短信发送状态服务
		registerReceiver(sendStatusReceiver, intentFilter2);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(smsReceiver);
		unregisterReceiver(sendStatusReceiver);
	}

	/**
	 * 监听收到信息广播
	 * 
	 * @author huang
	 */
	class MySmsReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			// 提取短信消息
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for (int i = 0; i < messages.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
			}
			// 获取发送号码
			String address = messages[0].getOriginatingAddress();
			// 获取发送内容
			StringBuilder contentStr = new StringBuilder();
			for (SmsMessage smsMessage : messages) {
				contentStr.append(smsMessage.getMessageBody());
			}
			sender.setText(address);
			content.setText(contentStr.toString());

			// 拦截信息
			abortBroadcast();
		}
	}

	/**
	 * 监听短信发送结果广播
	 * 
	 * @author huang
	 */
	class SendStatusReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (getResultCode() == RESULT_OK) {
				Toast.makeText(context, "消息发送成功...	", Toast.LENGTH_SHORT)
						.show();
				sendMsg.setText("");
			} else {
				Toast.makeText(context, "消息发送失败...	", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
}
