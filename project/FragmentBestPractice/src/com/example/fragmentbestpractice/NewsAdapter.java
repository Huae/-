package com.example.fragmentbestpractice;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<News> {
	private int resourceId;
	public NewsAdapter(Context context, int resource, List<News> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		News newsItem = getItem(position);
		View view;
		if(convertView == null){
			view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
		}else{
			view = convertView;
		}
		TextView titleView = (TextView) view.findViewById(R.id.news_title_string);
		titleView.setText(newsItem.getTitle());
		return view;
	}
}
