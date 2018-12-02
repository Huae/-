package com.huae.testHeWeather;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.heweather.sdk.api.HeConfig;
import com.heweather.sdk.api.HeWeather;
import com.heweather.sdk.bean.weather.now.Now;
import com.heweather.sdk.util.Callback;

public class TestHeWeather {
	public static void main(String[] args) {
		HeConfig.init("HE1811181827211260", "5a2cf2a3727c43528fadb371ddd5d4c0");
		HeConfig.switchToFreeServerNode();
		
		HeWeather.s6Now("116.565,40.1728", new Callback<List<Now>>() {
			
			public void onSuccess(List<Now> value) {
				System.out.println(JSON.toJSONString(value));
			}
			
			public void onError(Throwable e) {
				e.printStackTrace();
			}
		});
	}
}
