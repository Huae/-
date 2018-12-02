package com.example.playvideotest;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends Activity implements OnClickListener{
	private Button play;
	private Button replay;
	private Button pause;
	private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        play = (Button) findViewById(R.id.play);
        replay = (Button) findViewById(R.id.replay);
        pause = (Button) findViewById(R.id.pause);
        videoView = (VideoView) findViewById(R.id.video);
        
        play.setOnClickListener(this);
        replay.setOnClickListener(this);
        pause.setOnClickListener(this);
        
        initVideoView();
    }


    private void initVideoView() {
    	File file = new File(Environment.getExternalStorageDirectory(),"video.mp4");
    	videoView.setVideoPath(file.getPath());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play:
			if(!videoView.isPlaying()){
				videoView.start();
			}
			break;
		case R.id.pause:
			if(videoView.isPlaying()){
				videoView.pause();
			}
			break;
		case R.id.replay:
			if(videoView.isPlaying()){
				videoView.resume();
			}else{
				videoView.start();
				videoView.resume();
			}
			break;

		default:
			break;
		}
	}
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	if(videoView != null){
    		videoView.suspend();
    	}
    }
}
