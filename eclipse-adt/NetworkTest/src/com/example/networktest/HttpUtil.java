package com.example.networktest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络请求工具类
 * 
 * @author huang
 * 
 */
public class HttpUtil {
	public static void sendHttpRequest(final String address,
			final HttpCallbackListener listener) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpURLConnection httpURLConnection = null;
				try {
					URL url = new URL(address);
					httpURLConnection = (HttpURLConnection) url
							.openConnection();

					httpURLConnection.setRequestMethod("GET");
					httpURLConnection.setConnectTimeout(10000);
					httpURLConnection.setReadTimeout(10000);
					httpURLConnection.setDoInput(true);
					httpURLConnection.setDoOutput(true);
					
					// POST提交时添加参数
					//httpURLConnection.setRequestMethod("POST");
					// DataOutputStream outputStream = new
					// DataOutputStream(httpURLConnection.getOutputStream());
					// outputStream.writeBytes("username=admin&password=123456");

					InputStream inputStream = httpURLConnection
							.getInputStream();
					String line = "";
					StringBuilder response = new StringBuilder();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(inputStream));
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					if (listener != null) {
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					if (listener != null) {
						listener.onError(e);
					}
				} finally {
					if (httpURLConnection != null) {
						httpURLConnection.disconnect();
					}
				}
			}
		}).start();
	}
}
