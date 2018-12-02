package com.example.fragmentbestpractice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NewsContentActivity extends Activity {
	
	/** intent跳转方法*/
	public static void actionStart(Context context,String title,String content){
		Intent intent = new Intent(context, NewsContentActivity.class);
		intent.putExtra("news_title", title);
		intent.putExtra("news_content", content);
		context.startActivity(intent);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_content_layout);
		Intent intent = getIntent();
		String newsTitle = intent.getStringExtra("news_title");
		String newsContent = intent.getStringExtra("news_content");
		// 更新数据
		NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_layout);
		newsContentFragment.refreshData(newsTitle, newsContent);
	}
}
