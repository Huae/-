package com.example.locationtest;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView location;
	private String provider;
	private LocationManager manager;
	LocationListener listener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onLocationChanged(Location location) {
			showLocation(location);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		location = (TextView) findViewById(R.id.location);

		manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		List<String> allProviders = manager.getProviders(true);
		if (allProviders.contains(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		} else if (allProviders.contains(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
		} else {
			Toast.makeText(this, "no provider to use", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		Location lastKnownLocation = manager.getLastKnownLocation(provider);
		if (lastKnownLocation != null) {
			showLocation(lastKnownLocation);
		}
		manager.requestLocationUpdates(provider, 5000, 10, listener);
	}

	private void showLocation(Location lastKnownLocation) {
		final double latitude = lastKnownLocation.getLatitude(); // 纬度
		final double longitude = lastKnownLocation.getLongitude(); // 经度
		location.setText("经度:" + longitude + "\n纬度:" + latitude);

		// 获取位置
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					HttpClient client = new DefaultHttpClient();
					String url = "http://api.map.baidu.com/geocoder/v2/?location="
							+ latitude
							+ ","
							+ longitude
							+ "&output=json&pois=1&ak=hAwH16yU5f1KmWLI1NQcGc4yl7cQE0ag&mcode=86:FB:ED:AE:4E:60:1D:08:7A:2B:77:E3:9D:5C:D5:9F:D4:0A:D0:07;com.example.baidumaptest&pois=0";
					HttpGet get = new HttpGet(url);
					HttpResponse httpResponse = client.execute(get);

					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						HttpEntity entity = httpResponse.getEntity();
						String content = EntityUtils.toString(entity, "UTF-8");
						Log.i(MainActivity.class.getSimpleName(), url);
						Log.i(MainActivity.class.getSimpleName(), content);
					}
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (manager != null) {
			manager.removeUpdates(listener);
		}
	}
}
