package com.example.blaze;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class SimpleBrowser extends Activity implements OnClickListener {

	EditText url;
	WebView ourBrow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browser);
		
		ourBrow = (WebView) findViewById(R.id.wvBrowser);
		
		ourBrow.getSettings().setJavaScriptEnabled(true);
		ourBrow.getSettings().setLoadWithOverviewMode(true);
		ourBrow.getSettings().setUseWideViewPort(true);
		
		try {
			ourBrow.loadUrl("http://www.google.com");
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		
		
		ourBrow.setWebViewClient(new ourViewClient());
		
		Button go = (Button) findViewById(R.id.bGo);
		Button back = (Button) findViewById(R.id.bBack);
		Button forward = (Button) findViewById(R.id.bForward);
		Button refresh = (Button) findViewById(R.id.bRefresh);
		Button clearHistory = (Button) findViewById(R.id.bClearHistory);
		
		url = (EditText) findViewById(R.id.etURL);
		
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		forward.setOnClickListener(this);
		refresh.setOnClickListener(this);
		clearHistory.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bGo:
			String theWebsite = url.getText().toString();
			ourBrow.loadUrl(theWebsite);
			//hiding the keyboard after using the edit text
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
			
			break;
		case R.id.bBack:
			if (ourBrow.canGoBack()) 
				ourBrow.goBack();
			break;
			
		case R.id.bForward:
			if (ourBrow.canGoForward())
				ourBrow.goForward();
			break;
			
		case R.id.bRefresh:
			ourBrow.reload();
			break;
		case R.id.bClearHistory:
			ourBrow.clearHistory();
			break;
				
		}
	}
	
}
