package com.example.uiwidgettest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private Button button;
	private EditText editText;
	private ImageView imageView;
	private ProgressBar progressBar;
	private ProgressBar progressBar2;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button = (Button) findViewById(R.id.button);
		editText = (EditText) findViewById(R.id.edit_text);
		imageView = (ImageView) findViewById(R.id.image_view);
		progressBar = (ProgressBar) findViewById(R.id.progress_bar1);
		progressBar2 = (ProgressBar) findViewById(R.id.progress_bar2);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		button6 = (Button) findViewById(R.id.button6);
		
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		button5.setOnClickListener(this);
		button6.setOnClickListener(this);
		
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(MainActivity.this,
				// editText.getText().toString(), Toast.LENGTH_SHORT).show();
				// 获取EditText文本

				// imageView.setImageResource(R.drawable.ic_launcher); //
				// 更改imageView图片

				/*
				 * if(progressBar.getVisibility() == View.GONE){
				 * progressBar.setVisibility(View.VISIBLE); }else{
				 * progressBar.setVisibility(View.GONE); } 控制进度条的显示和隐藏
				 */

				// 控制进度条进度
				/*
				 * int progress = progressBar2.getProgress(); progress += 10;
				 * progressBar2.setProgress(progress);
				 */

				// 弹出对话框
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						MainActivity.this);
				dialog.setTitle("对话框")
						.setMessage("我是对话框")
						.setCancelable(false)
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

									}
								});
				dialog.show();
			}
		});
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
				progressDialog.setTitle("进度条对话框");
				progressDialog.setMessage("加载中...");
				//progressDialog.setCancelable(false);  设置后  必须手动关闭该对话框
				progressDialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button3:
			Intent intent3 = new Intent(MainActivity.this, LinearLayout.class);
			startActivity(intent3);
			break;
		case R.id.button4:
			Intent intent4 = new Intent(MainActivity.this, RelativeLayout.class);
			startActivity(intent4);
			break;
		case R.id.button5:
			Intent intent5 = new Intent(MainActivity.this, FrameLayout.class);
			startActivity(intent5);
			break;
		case R.id.button6:
			Intent intent6 = new Intent(MainActivity.this, TableLayout.class);
			startActivity(intent6);
			break;

		default:
			break;
		}
	}
}
