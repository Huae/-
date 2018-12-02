package com.example.globalcontext.util;

import android.util.Log;

/**
 * 自定一日志工具
 * 实现工具的热开关
 * @author huang
 *
 */
public class LogUtil {
	public static final int VERBOSE = 0;
	public static final int DEBUG = 1;
	public static final int INFO = 2;
	public static final int WRAN = 3;
	public static final int ERROR = 4;
	public static final int NOTHING = 5;
	public static final int LEVEL = VERBOSE;
	
	public static void v(String tag, String msg){
		if(LEVEL <= VERBOSE){
			Log.v(tag, msg);
		}
	}
	public static void d(String tag, String msg){
		if(LEVEL <= DEBUG){
			Log.v(tag, msg);
		}
	}
	
	// ... LEVEL指定为NOTHING时不打印
}
