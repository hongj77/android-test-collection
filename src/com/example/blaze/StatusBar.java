package com.example.blaze;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StatusBar extends Activity implements OnClickListener{
	
	NotificationManager nm;
	static final int uniqueID = 1394885;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statusbar);
		Button stat = (Button) findViewById(R.id.bStatusBar);
		stat.setOnClickListener(this);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.cancel(uniqueID);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, StatusBar.class);
		PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
		String body = "This is a message from Hong";
		String title = "Hong J.";
		Notification n = new Notification(R.drawable.notif, body, System.currentTimeMillis());
		n.setLatestEventInfo(this,title, body, pi);
		n.defaults = Notification.DEFAULT_ALL;
		
		nm.notify(uniqueID, n);
		finish();
		
	}

}
