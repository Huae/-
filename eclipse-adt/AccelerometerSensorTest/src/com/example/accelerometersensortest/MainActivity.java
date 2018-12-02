package com.example.accelerometersensortest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        
        Sensor sensor =  sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        sensorManager.registerListener(listener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    private SensorEventListener listener = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			float xValue = event.values[0];
			float yValue = event.values[1];
			float zValue = event.values[2];
			
			if(Math.abs(xValue)>15 ||  Math.abs(yValue)>15 || Math.abs(zValue)>15){
				Toast.makeText(MainActivity.this, "摇一摇", Toast.LENGTH_SHORT).show();
			}
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
	};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    protected void onPause() {
    	super.onPause();
    	sensorManager.unregisterListener(listener);
    }
}
