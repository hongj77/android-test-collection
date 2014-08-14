package com.example.blaze;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener{
	
	float x, y, sensorX, sensorY;
	Bitmap ball;
	SensorManager sm;
	MyBringBackSurface ourSurfaceView;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
			
		}
		
		
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.sun);
		x = y = sensorX = sensorY = 0;
		
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.resume();
		setContentView(ourSurfaceView);
		
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent e) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(16);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sensorX = e.values[0];
		sensorY = e.values[1];
	}
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		sm.unregisterListener(this);
		super.onPause();
		
	}



	private class MyBringBackSurface extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = false;

		public MyBringBackSurface(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			ourHolder = getHolder();
			resume();

		}

		public void pause() {
			isRunning = false;
			while (true) {
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			ourThread = null;
		}

		public void resume() {
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isRunning) {
				if (!ourHolder.getSurface().isValid()) {
					continue;

				}
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(02, 02, 150);
				float centerX = canvas.getWidth()/2;
				float centerY = canvas.getWidth()/2;
				
				canvas.drawBitmap(ball, centerX-ball.getWidth()/2 - sensorX*50, centerY-ball.getHeight()/2 + sensorY*50, null);
				

				ourHolder.unlockCanvasAndPost(canvas);
			}
		}

	}

}
