package com.example.uibestpractice;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MsgAdapter extends ArrayAdapter<Msg> {

	/** �����ļ�. */
	private int layoutId;

	public MsgAdapter(Context context, int resource, List<Msg> objects) {
		super(context, resource, objects);
		layoutId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ��ȡ��Ϣ
		Msg item = getItem(position);
		// ������ͼ��
		View view;
		ViewHolder viewHolder;
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(layoutId, null);
			// ʵ������ͼ
			viewHolder = new ViewHolder();
			viewHolder.rightLayout = (LinearLayout) view
					.findViewById(R.id.msg_left_layout);
			viewHolder.leftLayout = (LinearLayout) view
					.findViewById(R.id.msg_right_layout);
			viewHolder.leftMsg = (TextView) view.findViewById(R.id.msg_receive);
			viewHolder.rightMsg = (TextView) view.findViewById(R.id.msg_send);
			// ����ʵ��
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		// ������Ϣ��type���ض�Ӧ��ͼ
		if (item.getType() == Msg.TYPE_RECEIVED) {
			viewHolder.rightLayout.setVisibility(View.GONE);
			viewHolder.leftLayout.setVisibility(View.VISIBLE);
			viewHolder.leftMsg.setText(item.getMsg());
		} else {
			viewHolder.leftLayout.setVisibility(View.GONE);
			viewHolder.rightLayout.setVisibility(View.VISIBLE);
			viewHolder.rightMsg.setText(item.getMsg());
		}
		return view;
	}

	class ViewHolder {
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		TextView leftMsg;
		TextView rightMsg;
	}
}
