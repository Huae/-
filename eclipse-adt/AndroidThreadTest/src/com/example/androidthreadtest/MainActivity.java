package com.example.androidthreadtest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	protected static final int UPDATE_TEXT = 0;
	private TextView tv1;
	private Button btn1;
	private ProgressBar pb1;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_TEXT:
				tv1.setText("Nice...");
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv1 = (TextView) findViewById(R.id.tv1);
		btn1 = (Button) findViewById(R.id.btn1);
		pb1 = (ProgressBar) findViewById(R.id.pb1);

		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				/*
				 * new Thread(new Runnable() {
				 * 
				 * @Override public void run() { tv1.setText("NICE"); }
				 * }).start();
				 */// 子线程不能更新UI

				Message msg = new Message();
				msg.what = UPDATE_TEXT;
				handler.sendMessage(msg);
			}
		});
		new MyTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class MyTask extends AsyncTask<Void, Integer, Boolean> {

		@Override
		protected void onPostExecute(Boolean result) {
			Toast.makeText(MainActivity.this, "下载完成...", Toast.LENGTH_SHORT).show();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			pb1.setProgress(values[0]);
			tv1.setText("已下载:"+values[0]+"%");
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			int progress = 0;
			try {
				while (progress < 100) {
					int tmp = (int)((10+1)*Math.random());
					progress += tmp;
					Thread.sleep(500);
					if(progress > 100){
						progress = 100;
					}
					publishProgress(progress);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}

	}
}
