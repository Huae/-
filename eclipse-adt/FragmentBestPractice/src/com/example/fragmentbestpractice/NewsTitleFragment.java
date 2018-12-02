package com.example.fragmentbestpractice;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsTitleFragment extends Fragment implements OnItemClickListener {
	/** 新闻数据 */
	private ArrayList<News> newsData;
	/** 显示标题的ListView的适配器 */
	private ArrayAdapter<News> adapter;
	/** 显示标题的ListView */
	private ListView newsListView;
	/** 显示的面板类型 true:平板 false:手机 */
	private boolean isTwoPane;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// 初始化数据
		newsData = initNewsData();
		// 初始化适配器
		adapter = new NewsAdapter(activity, R.layout.news_title_item, newsData);
		/*
		 * adapter = new
		 * ArrayAdapter<News>(activity,android.R.layout.simple_list_item_2,
		 * newsData);
		 */
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// 界面绘制完成 知道界面状态 所以可以判断界面的类型了
		if (getActivity().getFragmentManager().findFragmentById(R.id.news_content_layout) != null) {
			isTwoPane = true;
		} else {
			isTwoPane = false;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 加载包含newsListView的界面
		View titleLayoutView = inflater.inflate(R.layout.news_title_layout,
				container, false);
		// 初始化显示标题的ListView
		newsListView = (ListView) titleLayoutView
				.findViewById(R.id.news_title_list_view);
		// 设置适配器
		newsListView.setAdapter(adapter);
		newsListView.isClickable();
		// 绑定点击事件
		newsListView.setOnItemClickListener(this);

		return titleLayoutView;
	}

	private ArrayList<News> initNewsData() {
		ArrayList<News> news = new ArrayList<News>();
		for(int i = 0;i<100;i++){
			news.add(new News("标题"+(i+1), "内容"+(i+1)));
		}
		return news;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		News newsItem = newsData.get(arg2);
		if (isTwoPane) {
			// 平板
			NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager()
					.findFragmentById(R.id.news_content_layout);
			newsContentFragment.refreshData(newsItem.getTitle(),
					newsItem.getContent());
		} else {
			// 手机
			NewsContentActivity.actionStart(getActivity(), newsItem.getTitle(),
					newsItem.getContent());
		}
	}
}
