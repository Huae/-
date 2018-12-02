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
	 * �ڹ��췽���м��ز��ֲ�Ϊ�ؼ����¼�
	 * @param context
	 * @param attrs
	 */
	public TitleLayout(final Context context, AttributeSet attrs) {
		super(context, attrs);
		// ���ز��� ����һ�����ֵ�id ��������ָ�������ز��ֵĸ�����
		LayoutInflater.from(context).inflate(R.layout.title, this);
		// Ϊ�ؼ����¼�
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
