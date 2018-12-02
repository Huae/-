package com.huae.activitytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ThirdActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_activity);
		
		Log.i("taskId", getTaskId()+"");
		TextView tv1 = (TextView) findViewById(R.id.tv1);
		
		Intent intent = getIntent();
		/*String stringExtra = intent.getStringExtra("param1");
		String stringExtra2 = intent.getStringExtra("param2");
		tv1.setText(stringExtra+"  "+stringExtra2);*/
		
		tv1.setText(intent.getStringExtra("extra_data"));
	}
	
	public void returnResults(View v){
		Intent intent = new Intent();
		intent.putExtra("data_return", "我是ThridActivity返回的数据...");
		setResult(RESULT_OK, intent);
		finish();
	}
	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putExtra("data_return", "我是ThridActivity返回的数据...");
		setResult(RESULT_OK, intent);
		finish();
	}
	public static void actionStart(Context context,String data1,String data2){
		Intent intent = new Intent(context,ThirdActivity.class);
		intent.putExtra("param1", data1);
		intent.putExtra("param2", data2);
		context.startActivity(intent);
	}
}
