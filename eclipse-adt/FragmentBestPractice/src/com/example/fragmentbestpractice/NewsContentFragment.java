package com.example.fragmentbestpractice;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsContentFragment extends Fragment {
	private static View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.news_content_fragment,container,false);
		return view;
	}
	
	/**
	 * 更新fragment数据
	 * @param title
	 * @param content
	 */
	public void refreshData(String title, String content){
		TextView titleView = (TextView) view.findViewById(R.id.content_title_text_view);
		TextView contentView = (TextView) view.findViewById(R.id.content_content_text_view);
		
		titleView.setText(title);
		contentView.setText(content);
	}
}
