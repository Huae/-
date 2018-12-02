package com.example.playaudiotest;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button play;
	private Button stop;
	private Button pause;
	private MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);
        pause = (Button) findViewById(R.id.pause);
        
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        pause.setOnClickListener(this);
        
        initMediaPlay();
    }


    private void initMediaPlay() {
    	File file = new File(Environment.getExternalStorageDirectory().getPath()+"/music.mp3");
    	try {
			mediaPlayer.setDataSource(file.getPath());
			mediaPlayer.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play:
			if(!mediaPlayer.isPlaying()){
				mediaPlayer.start();
			}
			break;
		case R.id.pause:
			if(mediaPlayer.isPlaying()){
				mediaPlayer.pause();
			}
			break;
		case R.id.stop:
			if(mediaPlayer.isPlaying()){
				mediaPlayer.reset();
				initMediaPlay();
			}
			break;

		default:
			break;
		}
	}
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	if(mediaPlayer != null){
    		mediaPlayer.stop();
    		mediaPlayer.release();
    	}
    }
}
