package com.example.test;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NewsTitleAdapter extends ArrayAdapter<String> {

	private int resourceId;
	public NewsTitleAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String title = getItem(position);
		View view;
		if(convertView == null){
			view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
		}else{
			view = convertView;
		}
		TextView titleString = (TextView) view.findViewById(R.id.news_title_string);
		titleString.setText(title);
		return view;
	}
}
