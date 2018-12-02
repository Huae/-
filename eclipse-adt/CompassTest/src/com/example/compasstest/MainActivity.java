package com.example.compasstest;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView arrow;
	private Button button;
	private SensorManager sensorManager;
	private SensorEventListener listener = new SensorEventListener() {
		float[] accelerometerValues = new float[3];
		float[] magneticValues = new float[3];
		float lastDegree;
		@Override
		public void onSensorChanged(SensorEvent event) {
			/*float zValue = event.values[0];
			float xValue = event.values[1];
			float yValue = event.values[2];
			
			Log.i(MainActivity.class.getSimpleName(), "zValue:"+zValue);
			Log.i(MainActivity.class.getSimpleName(), "xValue:"+xValue);
			Log.i(MainActivity.class.getSimpleName(), "yValue:"+yValue);*/
			if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
				accelerometerValues = event.values.clone();
			}else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
				magneticValues = event.values.clone();
			}
			float[] R = new float[9];
			SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticValues);
			
			float[] values = new float[3];	// 保存各个方向的旋转值/弧度
			SensorManager.getOrientation(R, values);
			
			Log.i(MainActivity.class.getSimpleName(), "zValue:"+Math.toDegrees(values[0]));
			Log.i(MainActivity.class.getSimpleName(), "xValue:"+Math.toDegrees(values[1]));
			Log.i(MainActivity.class.getSimpleName(), "yValue:"+Math.toDegrees(values[2]));
			
			float degree = -(float) Math.toDegrees(values[0]);
			if(Math.abs(degree - lastDegree) >1){
				// 旋转
				RotateAnimation rotateAnimation = new RotateAnimation(0, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//				rotateAnimation.setDuration(3000);
				rotateAnimation.setFillAfter(true);
				arrow.startAnimation(rotateAnimation);
				
				lastDegree = degree;
			}
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
      /*  Sensor orientation = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        sensorManager.registerListener(listener, orientation,SensorManager.SENSOR_DELAY_NORMAL);*/
        
        Sensor accelerate = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);	// 加速度传感器
        Sensor magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);	// 地磁传感器
        
        // 注册传感器
        sensorManager.registerListener(listener, accelerate,SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(listener, magnetic,SensorManager.SENSOR_DELAY_GAME);
        
        arrow = (ImageView) findViewById(R.id.imageView2);
        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				RotateAnimation rotateAnimation = new RotateAnimation(0, -360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				rotateAnimation.setDuration(3000);
				rotateAnimation.setFillAfter(true);
				arrow.startAnimation(rotateAnimation);
			}
		});
    }
    @Override
    protected void onPause() {
    	super.onPause();
    	if(sensorManager != null){
    		sensorManager.unregisterListener(listener);
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
