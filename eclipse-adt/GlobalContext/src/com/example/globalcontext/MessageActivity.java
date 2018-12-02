package com.example.globalcontext;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acticity_message);
		
		//People p = (People) getIntent().getSerializableExtra("extra_data");
		
		People p = getIntent().getParcelableExtra("extra_data");
		((TextView)findViewById(R.id.tv1)).setText("name:"+p.getName()+"\nage:"+p.getAge());
	}
}
