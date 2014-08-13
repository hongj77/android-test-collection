package com.example.blaze;

import java.io.IOException;
import java.io.InputStream;

import com.example.blaze.R.id;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;

public class Camera extends Activity implements View.OnClickListener{
	Intent i;
	final static int cameraData = 0;
	ImageView iv;
	ImageView ib;
	Button b;
	Bitmap bmp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialize();
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		bmp = BitmapFactory.decodeStream(is);
	}
	
	private void initialize() {
		iv = (ImageView) findViewById(R.id.ivReturnedPic);
		ib = (ImageView) findViewById(R.id.ibTakePic);
		b = (Button) findViewById(R.id.bSetWall);
		b.setOnClickListener(this);
		ib.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSetWall:
			
			WallpaperManager wpManager= WallpaperManager.getInstance(getApplicationContext());
			try {
				wpManager.setBitmap(bmp);
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			break;
			
		case R.id.ibTakePic:
			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i, cameraData);
			break;
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			iv.setImageBitmap(bmp);
		}
	}
	
	
	
}
