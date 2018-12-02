package com.example.uicustomerviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TitleLayout extends LinearLayout {

	/**
	 * 在构造方法中加载布局并为控件绑定事件
	 * @param context
	 * @param attrs
	 */
	public TitleLayout(final Context context, AttributeSet attrs) {
		super(context, attrs);
		// 加载布局 参数一：布局的id 参数二：指定所加载布局的父布局
		LayoutInflater.from(context).inflate(R.layout.title, this);
		// 为控件绑定事件
		Button back = (Button) findViewById(R.id.title_back);
		Button edit = (Button) findViewById(R.id.title_edit);
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((Activity)getContext()).finish();
			}
		});
		edit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, "edit........", Toast.LENGTH_SHORT).show();
			}
		});
	}

}
