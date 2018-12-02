package com.huae.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class SecondActivity extends BaseActivity {
	private Button back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		Log.i("taskId", getTaskId()+"");
		setContentView(R.layout.second_activity);
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//finish();
				Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
				startActivity(intent);
			}
		});
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("SecondActivity", "onDestroy");
	}
	
}
