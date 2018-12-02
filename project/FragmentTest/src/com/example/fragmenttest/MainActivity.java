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
		 * �ڻ�е���fragment�ķ���
		 * Fragment fragment = getFragmentManager().findFragmentById(R.id.right_fragment);
		 * fragment.xxx();
		 * 
		 * ����Ƭ�е��û�ķ���
		 * activity = (ǿת)getActivity();
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
				transaction.addToBackStack(null); // ��ӷ���ջ �����л�fragment��
													// ��back��ֱ���˳�����
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
