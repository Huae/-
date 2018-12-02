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
	/** �������� */
	private ArrayList<News> newsData;
	/** ��ʾ�����ListView�������� */
	private ArrayAdapter<News> adapter;
	/** ��ʾ�����ListView */
	private ListView newsListView;
	/** ��ʾ��������� true:ƽ�� false:�ֻ� */
	private boolean isTwoPane;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// ��ʼ������
		newsData = initNewsData();
		// ��ʼ��������
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

		// ���������� ֪������״̬ ���Կ����жϽ����������
		if (getActivity().getFragmentManager().findFragmentById(R.id.news_content_layout) != null) {
			isTwoPane = true;
		} else {
			isTwoPane = false;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// ���ذ���newsListView�Ľ���
		View titleLayoutView = inflater.inflate(R.layout.news_title_layout,
				container, false);
		// ��ʼ����ʾ�����ListView
		newsListView = (ListView) titleLayoutView
				.findViewById(R.id.news_title_list_view);
		// ����������
		newsListView.setAdapter(adapter);
		newsListView.isClickable();
		// �󶨵���¼�
		newsListView.setOnItemClickListener(this);

		return titleLayoutView;
	}

	private ArrayList<News> initNewsData() {
		ArrayList<News> news = new ArrayList<News>();
		for(int i = 0;i<100;i++){
			news.add(new News("����"+(i+1), "����"+(i+1)));
		}
		return news;
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		News newsItem = newsData.get(arg2);
		if (isTwoPane) {
			// ƽ��
			NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager()
					.findFragmentById(R.id.news_content_layout);
			newsContentFragment.refreshData(newsItem.getTitle(),
					newsItem.getContent());
		} else {
			// �ֻ�
			NewsContentActivity.actionStart(getActivity(), newsItem.getTitle(),
					newsItem.getContent());
		}
	}
}
