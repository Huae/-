package com.example.globalcontext;

import android.app.Application;
import android.content.Context;

/**
 * 全局获取context
 * 		:需要在清单文件中注册
 * @author huang
 *
 */
public class MyApplication extends Application{
	private static Context context;
	@Override
	public void onCreate() {
		context = getApplicationContext();
	}
	public static Context getContext(){
		return context;
	}
}
