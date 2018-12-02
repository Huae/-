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
		
		
		// Ϊ��ť����¼�����
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

		// Log.i("��׼ģʽ", "��ǰactivityΪ��"+this.toString());
		// ÿ��intent��ת�����´���activity����ջ��

		// Log.i("singleTopģʽ", "��ǰactivityΪ��" + this.toString());
		// ջ���Ѿ��ǵ�ǰactivityʱֱ��ʹ��ʹ�ø�activity
		// ���������´���activiy�ŵ�ջ��(ջ�����ظ���activity)

		// Log.i("singleTaskģʽ", "��ǰactivityΪ��" + this.toString());
		// ����ջ�м���Ƿ�����Ӧ��activityʵ�����о�ֱ��ʹ��
		// ���Ѹ�activity���������activity��ջ
		// û�оʹ����µ�activity

		Log.i("singleInstanceģʽ", "��ǰactivityΪ��" + this.toString()); // Ϊ��ǰactivity���������µ�ջ
				// ����ʵ�ֶ��Ӧ�ù����ʵ�����������Լ���ջ�д����µ�ʵ��
		
		// ��ӡ��ǰջid
		Log.i("taskId",getTaskId()+"");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// �����˵� ����һ���˵��ļ� ���������˵�Ҫ��ӵ��ĸ�������
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/* *
	 * Ϊ�˵�������¼�
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
			// ��ʾintent�򿪻2
			Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
			startActivity(intent);
			break;
		case R.id.btn4:
			// ��ʽintent�򿪻
			Intent intent2 = new Intent("com.huae.activitytest.ACTION_START");
			intent2.addCategory("com.huae.activitytest.MY_CATEGORY");
			startActivity(intent2);
			break;
		case R.id.btn5:
			// ��ʽintent�����÷�
			Intent intent3 = new Intent(Intent.ACTION_VIEW); // ָ��action
			intent3.setData(Uri.parse("http://www.baidu.com")); // ��������
																// ֻ����ƥ�䵱ǰUri���ݵ�activity������Ӧ��ǰintent
			startActivity(intent3);
			break;
		case R.id.btn6:
			Intent intent4 = new Intent(Intent.ACTION_DIAL);
			intent4.setData(Uri.parse("tel:10086"));
			startActivity(intent4);
			break;
		case R.id.btn7:
			// �򿪻 ����������
			Intent intent7 = new Intent(FirstActivity.this, ThirdActivity.class);
			intent7.putExtra("extra_data", "������FirstActivity...");
			startActivityForResult(intent7, 1);
			/*ThirdActivity.actionStart(FirstActivity.this, "data1", "data2");*/
			break;
		case R.id.btn8:
			// ��������ģʽ
			// Intent intent8 = new Intent(this, FirstActivity.class);
			Intent intent8 = new Intent(this, SecondActivity.class);
			startActivity(intent8);
			break;
		case R.id.btn9:
			// �ر�����
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
			// ����ɹ�
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
