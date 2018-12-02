package com.example.globalcontext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button serializable;
	private Button parcelable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toast.makeText(MyApplication.getContext(), "加载完成...",
				Toast.LENGTH_SHORT).show();

		serializable = (Button) findViewById(R.id.button1);
		parcelable = (Button) findViewById(R.id.button2);

		serializable.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// serializable方式
				Intent intent = new Intent(MyApplication.getContext(), MessageActivity.class);
				intent.putExtra("extra_data", new People("张三", 20));
				// 使用bundle
				//Bundle bundle = new Bundle();
				//bundle.putSerializable("extra_data", new People("张三bundle", 20));
				// bundle.putXxx("xxx",xxx);
				//intent.putExtras(bundle); 会覆盖之前添加的所有数据
				
				//intent.putExtra("bundle",bundle);	// 追加数据
				startActivity(intent);
			}
		});
		parcelable.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// parcelable方式
				Intent intent = new Intent(MyApplication.getContext(), MessageActivity.class);
				intent.putExtra("extra_data", new People("张三", 20));
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
