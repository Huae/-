package com.example.fragmenttest;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		 * 在活动中调用fragment的方法
		 * Fragment fragment = getFragmentManager().findFragmentById(R.id.right_fragment);
		 * fragment.xxx();
		 * 
		 * 在碎片中调用活动的方法
		 * activity = (强转)getActivity();
		 * activity.xxx();
		 */

		
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AnotherRightFragment anotherRightFragment = new AnotherRightFragment();
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction transaction = fragmentManager
						.beginTransaction();
				transaction.replace(R.id.right_fragment_layout,
						anotherRightFragment);
				transaction.addToBackStack(null); // 添加返回栈 避免切换fragment后
													// 按back键直接退出程序
				transaction.commit();
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
