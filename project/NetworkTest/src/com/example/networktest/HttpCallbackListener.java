package com.example.networktest;

public interface HttpCallbackListener {
	void onFinish(String content);
	void onError(Exception e);
}
