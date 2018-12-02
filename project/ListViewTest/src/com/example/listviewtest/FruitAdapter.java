package com.example.listviewtest;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listviewtest.bean.Fruit;

public class FruitAdapter extends ArrayAdapter<Fruit> {

	private int resourceId;

	public FruitAdapter(Context context, int textViewResourceId,
			List<Fruit> objects) {
		super(context, textViewResourceId, objects);
		// 获取布局
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 获取对应数据项
		Fruit item = getItem(position);
		// 加载布局
		View view;
		ViewHolder viewHolder;
		// 1. 解决每次getView是都重新加载view 提升效率
		if (convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);

			// 缓存实例
			viewHolder = new ViewHolder();
			viewHolder.fruitImage = (ImageView) view
					.findViewById(R.id.fruit_image);
			viewHolder.fruitName = (TextView) view
					.findViewById(R.id.fruit_name);

			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}

		// View view = LayoutInflater.from(getContext()).inflate(resourceId,
		// parent);
		// java.lang.UnsupportedOperationException: addView(View, LayoutParams)
		// is not supported in AdapterView

		// 设置数据
		/*
		 * ImageView fruitImage = (ImageView)
		 * view.findViewById(R.id.fruit_image);
		 * fruitImage.setImageResource(item.getImageId());
		 * 
		 * TextView fruitName = (TextView) view.findViewById(R.id.fruit_name);
		 * fruitName.setText(item.getFruitName());
		 */

		viewHolder.fruitImage.setImageResource(item.getImageId());
		viewHolder.fruitName.setText(item.getFruitName());
		return view;
	}

	// 2. 保存findViewById的实例 提升效率
	class ViewHolder {
		ImageView fruitImage;
		TextView fruitName;
	}
}
