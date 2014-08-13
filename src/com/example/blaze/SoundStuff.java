package com.example.blaze;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements OnClickListener, OnLongClickListener{
	
	SoundPool sp;
	int explosion;
	MediaPlayer mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View v = new View(this);
		v.setOnLongClickListener(this);
		v.setOnClickListener(this);
		setContentView(v);
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		mp = MediaPlayer.create(this, R.raw.twirl);
		explosion = sp.load(this, R.raw.twirl, 1);
		
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (explosion != 0)
			sp.play(explosion,1,1,0,0,2);
	}
	
	public boolean onLongClick(View arg0) {
		mp.start();
		return true;
	}
	
	

}
