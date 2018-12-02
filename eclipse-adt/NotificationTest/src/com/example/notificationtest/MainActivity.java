package com.example.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button button1;
	private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			Intent intent = new Intent(MainActivity.this,AnotherActivity.class);
			// 创建pendingintent
			PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent , PendingIntent.FLAG_UPDATE_CURRENT);
			// 获得通知管理器
			NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			// 创建通知
			Notification notification = new Notification(R.drawable.ic_launcher, "有新的通知哟...", System.currentTimeMillis());  //已废弃
			// 设置通知布局  参数四：点击通知后跳转或者其他操作...
			notification.setLatestEventInfo(this, "通知标题", "通知内容", pendingIntent);	// 已废弃
			// 设置通知的声音、振动、指示灯等属性
			notification.defaults = Notification.DEFAULT_ALL;
			/*	notification.sound = Uri.fromFile(new File("/system/media/sound/ringtones/Basic_tone.ogg"));
			notification.vibrate = new long[]{0,1000,1000,1000};  // 手机静止的时长、手机振动的时长...
			notification.ledARGB = Color.GREEN;
			notification.ledOnMS = 1000;
			notification.ledOffMS = 1000;
			notification.flags = Notification.FLAG_SHOW_LIGHTS;	// 显示指示灯
		 	*/			
			
			// 显示通知  参数一:通知id(用于取消通知等其他操作)  参数二: 通知对象
			manager.notify(1, notification);
			break;
		case R.id.button2:
			// 获得通知管理器
			NotificationManager manager2 = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			Intent intent2 = new Intent(MainActivity.this,AnotherActivity.class);
			PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent2 , PendingIntent.FLAG_UPDATE_CURRENT);
			// 创建通知
			Notification build = new NotificationCompat.Builder(this).setTicker("有新的通知哟...")
					.setAutoCancel(true).setContentTitle("通知标题").setContentIntent(pendingIntent2).setContentInfo("contentInfo").setDefaults(Notification.DEFAULT_ALL)
					.setContentText("标题内容").setSmallIcon(R.drawable.ic_launcher).build();
			manager2.notify(2, build);
			break;

		default:
			break;
		}
	}
    
}
