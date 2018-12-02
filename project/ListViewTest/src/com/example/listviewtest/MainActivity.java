package com.example.listviewtest;

import java.util.ArrayList;

import com.example.listviewtest.bean.Fruit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView listView;
	private ArrayList<Fruit> fruitist = new ArrayList<Fruit>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.list_view);
		initFruits();

		// 简单ListView 每行只有一个文本
		// simpleListView();

		// 复杂的ListView 显示图片和文本
		complexListView(listView);
		// 设置点击事件
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Fruit fruit = fruitist.get(position);
				new AlertDialog.Builder(MainActivity.this)
						.setTitle("详情")
						.setMessage(
								"你点击了'" + fruit.getFruitName() + "'  id"
										+ fruit.getImageId())
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

									}
								}).show();
			}
		});
	}

	private void complexListView(ListView listView) {
		FruitAdapter adapter = new FruitAdapter(MainActivity.this,
				R.layout.fruit_layout, fruitist);
		listView.setAdapter(adapter);
	}

	private void initFruits() {
		fruitist.add(new Fruit("apple", R.drawable.apple_pic));
		fruitist.add(new Fruit("banana", R.drawable.banana_pic));
		fruitist.add(new Fruit("cherry", R.drawable.cherry_pic));
		fruitist.add(new Fruit("grape", R.drawable.grape_pic));
		fruitist.add(new Fruit("mango", R.drawable.mango_pic));
		fruitist.add(new Fruit("orange", R.drawable.orange_pic));
		fruitist.add(new Fruit("pear", R.drawable.pear_pic));
		fruitist.add(new Fruit("pineapple", R.drawable.pineapple_pic));
		fruitist.add(new Fruit("strawberry", R.drawable.strawberry_pic));
		fruitist.add(new Fruit("watermelon", R.drawable.watermelon_pic));
		fruitist.add(new Fruit("apple", R.drawable.apple_pic));
		fruitist.add(new Fruit("banana", R.drawable.banana_pic));
		fruitist.add(new Fruit("cherry", R.drawable.cherry_pic));
		fruitist.add(new Fruit("grape", R.drawable.grape_pic));
		fruitist.add(new Fruit("mango", R.drawable.mango_pic));
		fruitist.add(new Fruit("orange", R.drawable.orange_pic));
		fruitist.add(new Fruit("pear", R.drawable.pear_pic));
		fruitist.add(new Fruit("pineapple", R.drawable.pineapple_pic));
		fruitist.add(new Fruit("strawberry", R.drawable.strawberry_pic));
		fruitist.add(new Fruit("watermelon", R.drawable.watermelon_pic));
		fruitist.add(new Fruit("apple", R.drawable.apple_pic));
		fruitist.add(new Fruit("banana", R.drawable.banana_pic));
		fruitist.add(new Fruit("cherry", R.drawable.cherry_pic));
		fruitist.add(new Fruit("grape", R.drawable.grape_pic));
		fruitist.add(new Fruit("mango", R.drawable.mango_pic));
		fruitist.add(new Fruit("orange", R.drawable.orange_pic));
		fruitist.add(new Fruit("pear", R.drawable.pear_pic));
		fruitist.add(new Fruit("pineapple", R.drawable.pineapple_pic));
		fruitist.add(new Fruit("strawberry", R.drawable.strawberry_pic));
		fruitist.add(new Fruit("watermelon", R.drawable.watermelon_pic));
		fruitist.add(new Fruit("apple", R.drawable.apple_pic));
		fruitist.add(new Fruit("banana", R.drawable.banana_pic));
		fruitist.add(new Fruit("cherry", R.drawable.cherry_pic));
		fruitist.add(new Fruit("grape", R.drawable.grape_pic));
		fruitist.add(new Fruit("mango", R.drawable.mango_pic));
		fruitist.add(new Fruit("orange", R.drawable.orange_pic));
		fruitist.add(new Fruit("pear", R.drawable.pear_pic));
		fruitist.add(new Fruit("pineapple", R.drawable.pineapple_pic));
		fruitist.add(new Fruit("strawberry", R.drawable.strawberry_pic));
		fruitist.add(new Fruit("watermelon", R.drawable.watermelon_pic));
		fruitist.add(new Fruit("apple", R.drawable.apple_pic));
		fruitist.add(new Fruit("banana", R.drawable.banana_pic));
		fruitist.add(new Fruit("cherry", R.drawable.cherry_pic));
		fruitist.add(new Fruit("grape", R.drawable.grape_pic));
		fruitist.add(new Fruit("mango", R.drawable.mango_pic));
		fruitist.add(new Fruit("orange", R.drawable.orange_pic));
		fruitist.add(new Fruit("pear", R.drawable.pear_pic));
		fruitist.add(new Fruit("pineapple", R.drawable.pineapple_pic));
		fruitist.add(new Fruit("strawberry", R.drawable.strawberry_pic));
		fruitist.add(new Fruit("watermelon", R.drawable.watermelon_pic));
		fruitist.add(new Fruit("apple", R.drawable.apple_pic));
		fruitist.add(new Fruit("banana", R.drawable.banana_pic));
		fruitist.add(new Fruit("cherry", R.drawable.cherry_pic));
		fruitist.add(new Fruit("grape", R.drawable.grape_pic));
		fruitist.add(new Fruit("mango", R.drawable.mango_pic));
		fruitist.add(new Fruit("orange", R.drawable.orange_pic));
		fruitist.add(new Fruit("pear", R.drawable.pear_pic));
		fruitist.add(new Fruit("pineapple", R.drawable.pineapple_pic));
		fruitist.add(new Fruit("strawberry", R.drawable.strawberry_pic));
		fruitist.add(new Fruit("watermelon", R.drawable.watermelon_pic));
	}

	private void simpleListView() {
		// 准备数据
		String[] data = { "Apple", "Banana", "Orange", "Watermelon", "Pear",
				"Grape", "Pineapple", "Strawberry", "Cherry", "Mango", "Apple",
				"Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple",
				"Strawberry", "Cherry", "Mango" };
		// 创建适配器 指定listView的上下文、每一项(行)的布局和内容
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1, data);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
