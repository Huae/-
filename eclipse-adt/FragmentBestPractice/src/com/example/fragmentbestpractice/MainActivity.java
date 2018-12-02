package com.example.fragmentbestpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       /* NewsContentFragment newsContentFragment =  (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_layout);
        newsContentFragment.refreshData("标题        阿斯顿发生 阿斯蒂芬阿斯蒂芬", "内同 啊手动阀手动阀手动阀手动阀手动阀");
        */
       /* ListView listView = (ListView) findViewById(R.id.news_title_list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(MainActivity.this, "saasdfasdf", Toast.LENGTH_SHORT).show();
			}
        	
        });
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Toast.makeText(MainActivity.this, "saasdfasdf"+"{}"+arg2, Toast.LENGTH_SHORT).show();
				return false;
			}
		});*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
