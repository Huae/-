package com.huae.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity implements View.OnClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first_activity);
		
		
		// 为按钮添加事件监听
		Button btn1 = (Button) findViewById(R.id.btn1);
		Button btn2 = (Button) findViewById(R.id.btn2);
		Button btn3 = (Button) findViewById(R.id.btn3);
		Button btn4 = (Button) findViewById(R.id.btn4);
		Button btn5 = (Button) findViewById(R.id.btn5);
		Button btn6 = (Button) findViewById(R.id.btn6);
		Button btn7 = (Button) findViewById(R.id.btn7);
		Button btn8 = (Button) findViewById(R.id.btn8);
		Button btn9 = (Button) findViewById(R.id.btn9);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);

		// Log.i("标准模式", "当前activity为："+this.toString());
		// 每次intent跳转都重新创建activity放在栈顶

		// Log.i("singleTop模式", "当前activity为：" + this.toString());
		// 栈顶已经是当前activity时直接使用使用该activity
		// 不是则重新创建activiy放到栈顶(栈中有重复的activity)

		// Log.i("singleTask模式", "当前activity为：" + this.toString());
		// 先在栈中检查是否有相应的activity实例，有就直接使用
		// 并把该activity上面的所有activity出栈
		// 没有就创建新的activity

		Log.i("singleInstance模式", "当前activity为：" + this.toString()); // 为当前activity单独创建新的栈
				// 可以实现多个应用共享该实例而不用在自己的栈中创建新的实例
		
		// 打印当前栈id
		Log.i("taskId",getTaskId()+"");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 创建菜单 参数一：菜单文件 参数二：菜单要添加到哪个对象上
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/* *
	 * 为菜单项添加事件
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_item:
			Toast.makeText(this, "add item", Toast.LENGTH_SHORT).show();
			break;
		case R.id.remove_item:
			Toast.makeText(this, "remove item", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			Toast.makeText(FirstActivity.this, "First Acticity!",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn2:
			finish();
			break;
		case R.id.btn3:
			// 显示intent打开活动2
			Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
			startActivity(intent);
			break;
		case R.id.btn4:
			// 隐式intent打开活动
			Intent intent2 = new Intent("com.huae.activitytest.ACTION_START");
			intent2.addCategory("com.huae.activitytest.MY_CATEGORY");
			startActivity(intent2);
			break;
		case R.id.btn5:
			// 隐式intent其他用法
			Intent intent3 = new Intent(Intent.ACTION_VIEW); // 指定action
			intent3.setData(Uri.parse("http://www.baidu.com")); // 设置数据
																// 只有能匹配当前Uri数据的activity才能响应当前intent
			startActivity(intent3);
			break;
		case R.id.btn6:
			Intent intent4 = new Intent(Intent.ACTION_DIAL);
			intent4.setData(Uri.parse("tel:10086"));
			startActivity(intent4);
			break;
		case R.id.btn7:
			// 打开活动 并传送数据
			Intent intent7 = new Intent(FirstActivity.this, ThirdActivity.class);
			intent7.putExtra("extra_data", "我来自FirstActivity...");
			startActivityForResult(intent7, 1);
			/*ThirdActivity.actionStart(FirstActivity.this, "data1", "data2");*/
			break;
		case R.id.btn8:
			// 测试启动模式
			// Intent intent8 = new Intent(this, FirstActivity.class);
			Intent intent8 = new Intent(this, SecondActivity.class);
			startActivity(intent8);
			break;
		case R.id.btn9:
			// 关闭所有
			ActivityController.finishAll();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			// 处理成功
			if (resultCode == RESULT_OK) {
				String returnData = data.getStringExtra("data_return");
				Toast.makeText(this, returnData, Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
	}
}
