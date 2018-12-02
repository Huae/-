package com.example.networktest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity {
	protected static final int SHOW_RESPONSE = 0;
	private Button httpUrl;
	private Button httpCli;
	private Button pullParse;
	private Button saxParse;
	private Button jsonArrayParse;
	private Button gsonParse;
	private TextView textView1;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESPONSE:
				textView1.setText((String) msg.obj);
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		httpUrl = (Button) findViewById(R.id.button1);
		httpCli = (Button) findViewById(R.id.button2);
		textView1 = (TextView) findViewById(R.id.textView1);

		pullParse = (Button) findViewById(R.id.button3);
		saxParse = (Button) findViewById(R.id.button4);
		jsonArrayParse = (Button) findViewById(R.id.button5);
		gsonParse = (Button) findViewById(R.id.button6);

		// 使用HttpUrlConnection连接
		httpUrl.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						HttpURLConnection httpURLConnection = null;
						try {
							URL url = new URL("http://www.baidu.com");
							httpURLConnection = (HttpURLConnection) url
									.openConnection();

							httpURLConnection.setRequestMethod("GET");
							httpURLConnection.setConnectTimeout(10000);
							httpURLConnection.setReadTimeout(10000);

							// POST提交时添加参数
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
							// 发送消息
							Message msg = new Message();
							msg.what = SHOW_RESPONSE;
							msg.obj = response.toString();

							handler.sendMessage(msg);
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (httpURLConnection != null) {
								httpURLConnection.disconnect();
							}
						}
					}
				}).start();
			}
		});

		// 使用HttpClient连接
		httpCli.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							HttpClient client = new DefaultHttpClient();
							HttpGet get = new HttpGet("http://www.baidu.com");
							HttpResponse httpResponse = client.execute(get);

							// post方式
							/*
							 * HttpPost post = new HttpPost();
							 * List<NameValuePair> params = new
							 * ArrayList<NameValuePair>(); params.add(new
							 * BasicNameValuePair("username","admin"));
							 * params.add(new
							 * BasicNameValuePair("password","123456"));
							 * UrlEncodedFormEntity entity = new
							 * UrlEncodedFormEntity(params, "utf-8");
							 * post.setEntity(entity); client.execute(post);
							 */

							if (httpResponse.getStatusLine().getStatusCode() == 200) {
								HttpEntity entity = httpResponse.getEntity();
								String content = EntityUtils.toString(entity,
										"UTF-8");

								// 发送消息
								Message msg = new Message();
								msg.what = SHOW_RESPONSE;
								msg.obj = content;

								handler.sendMessage(msg);
							}
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
			}
		});

		// pull解析xml
		pullParse.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					public void run() {
						try {
							HttpClient client = new DefaultHttpClient();
							HttpGet get = new HttpGet(
									"http://192.168.118.2/data.xml");
							HttpResponse httpResponse = client.execute(get);

							if (httpResponse.getStatusLine().getStatusCode() == 200) {
								HttpEntity entity = httpResponse.getEntity();
								String content = EntityUtils.toString(entity,
										"UTF-8");

								content = parseXmlWithPull(content);
								// 发送消息
								Message msg = new Message();
								msg.what = SHOW_RESPONSE;
								msg.obj = content;

								handler.sendMessage(msg);
							}
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();

			}
		});

		// sax解析xml
		saxParse.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					public void run() {
						try {
							HttpClient client = new DefaultHttpClient();
							HttpGet get = new HttpGet(
									"http://192.168.118.2/data.xml");
							HttpResponse httpResponse = client.execute(get);

							if (httpResponse.getStatusLine().getStatusCode() == 200) {
								HttpEntity entity = httpResponse.getEntity();
								String content = EntityUtils.toString(entity,
										"UTF-8");

								content = parseXmlWithSax(content);
								// 发送消息
								Message msg = new Message();
								msg.what = SHOW_RESPONSE;
								msg.obj = content;

								handler.sendMessage(msg);
							}
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}).start();

			}
		});

		// jsonarray解析json
		jsonArrayParse.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					public void run() {
						try {
							HttpClient client = new DefaultHttpClient();
							HttpGet get = new HttpGet(
									"http://192.168.118.2/data.json");
							HttpResponse httpResponse = client.execute(get);

							if (httpResponse.getStatusLine().getStatusCode() == 200) {
								HttpEntity entity = httpResponse.getEntity();
								String content = EntityUtils.toString(entity,
										"UTF-8");
								StringBuilder builder = new StringBuilder();
								// 解析json
								try {
									JSONArray array = new JSONArray(content);
									for (int i = 0; i < array.length(); i++) {
										JSONObject object = array.getJSONObject(i);
										builder.append("id:"+object.get("id")+"\n");
										builder.append("name:"+object.get("name")+"\n");
										builder.append("version:"+object.get("version")+"\n"+"\n");
									}
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// 发送消息
								Message msg = new Message();
								msg.what = SHOW_RESPONSE;
								msg.obj = builder.toString();

								handler.sendMessage(msg);
							}
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}).start();

			}
		});

		// GSON解析json
		gsonParse.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							HttpClient client = new DefaultHttpClient();
							HttpGet get = new HttpGet(
									"http://192.168.118.2/data.json");
							HttpResponse httpResponse = client.execute(get);

							if (httpResponse.getStatusLine().getStatusCode() == 200) {
								HttpEntity entity = httpResponse.getEntity();
								String content = EntityUtils.toString(entity,
										"UTF-8");
								StringBuilder builder = new StringBuilder();
								// 解析json
								Gson gson = new Gson();
								List<App> fromJson = gson.fromJson(content, new TypeToken<List<App>>() {}.getType());
								for (App app : fromJson) {
									builder.append("id:"+app.getId()+"\n");
									builder.append("name:"+app.getName()+"\n");
									builder.append("version:"+app.getVersion()+"\n"+"\n");
								}
								// 发送消息
								Message msg = new Message();
								msg.what = SHOW_RESPONSE;
								msg.obj = builder.toString();

								handler.sendMessage(msg);
							}
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();

			}
		});
	}

	protected String parseXmlWithPull(String content) {
		StringBuilder builder = new StringBuilder();
		try {
			// 创建工厂
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			// 获得解析器
			XmlPullParser parser = factory.newPullParser();
			// 设置需要解析的数据
			parser.setInput(new StringReader(content));
			// 解析事件类型
			int eventType = parser.getEventType();
			// 如果不是文档末尾
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String nodeName = parser.getName();
				switch (eventType) {
				// 解析节点
				case XmlPullParser.START_TAG:
					if ("id".equals(nodeName)) {
						builder.append("id:" + parser.nextText() + "\n");
					} else if ("name".equals(nodeName)) {
						builder.append("name:" + parser.nextText() + "\n");
					} else if ("version".equals(nodeName)) {
						builder.append("version:" + parser.nextText() + "\n"
								+ "\n");
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				default:
					break;
				}
				eventType = parser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	private String parseXmlWithSax(String content) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser newSAXParser = factory.newSAXParser();
			XMLReader xmlReader = newSAXParser.getXMLReader();
			MyHandler handler = new MyHandler();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new StringReader(content)));
			return handler.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void sendHttpRequest(View v){
		HttpUtil.sendHttpRequest("http://www.baidu.com", new HttpCallbackListener() {
			
			@Override
			public void onFinish(String content) {
				// 发送消息
				Message msg = new Message();
				msg.what = SHOW_RESPONSE;
				msg.obj = content;

				handler.sendMessage(msg);
			}
			
			@Override
			public void onError(Exception e) {
				e.printStackTrace();
			}
		});
	}
}
