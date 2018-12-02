package com.example.uibestpractice;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ArrayList<Msg> msgList = new ArrayList<Msg>();
	private ListView listView;
	private EditText editText;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.msg_list);
		editText = (EditText) findViewById(R.id.msg_edit);
		button = (Button) findViewById(R.id.msg_send);

		initMsgList();
		final MsgAdapter adapter = new MsgAdapter(MainActivity.this,
				R.layout.msg_layout, msgList);
		listView.setAdapter(adapter);

		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String msgStr = editText.getText().toString().trim();
				// 消息不为空
				if (!"".equals(msgStr)) {
					Msg msg = new Msg(msgStr, Msg.TYPE_SEND);
					msgList.add(msg);

					adapter.notifyDataSetChanged(); // 刷新ListView显示
					listView.setSelection(msgList.size()); // 重定位ListView消息位置

					editText.setText("");
				}
			}
		});
	}

	private void initMsgList() {
		msgList.add(new Msg("Hello1", Msg.TYPE_RECEIVED));
		msgList.add(new Msg("Hello2", Msg.TYPE_SEND));
		msgList.add(new Msg("Hello3", Msg.TYPE_RECEIVED));
		msgList.add(new Msg("Hello4", Msg.TYPE_SEND));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
