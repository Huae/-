package com.example.test;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class NewsTitleFragment extends Fragment implements OnItemClickListener,OnItemLongClickListener{
	View view;
	private ListView titleListView;
	private NewsTitleAdapter adapter;
	private ArrayList<String> titleData;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		initTitleData();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.news_title_layout, container, false);
		titleListView = (ListView) view.findViewById(R.id.news_title_list_view);
		adapter = new NewsTitleAdapter(getActivity(), R.layout.news_title_item,
				titleData);
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		titleListView.setAdapter(adapter);
		titleListView.setOnItemClickListener(this);
		titleListView.setOnItemLongClickListener(this);
	}
	private void initTitleData() {
		titleData = new ArrayList<String>();
		titleData.add("标题");
		titleData.add("标题");
		titleData.add("标题");
		titleData.add("标题");
		titleData.add("标题");
		titleData.add("标题");
		titleData.add("标题");
		titleData.add("标题");
		titleData.add("标题");
		titleData.add("标题");
		titleData.add("标题");
		titleData.add("标题");
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Toast.makeText(getActivity(), titleData.get(arg2)+"  "+arg2, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		Toast.makeText(getActivity(), titleData.get(arg2)+" ff "+arg2, Toast.LENGTH_SHORT).show();
		return false;
	}
}
