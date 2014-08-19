package com.example.blaze;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeatherXMLParsing extends Activity implements OnClickListener {
	
	static final String baseURL = "http://api.openweathermap.org/data/2.5/weather?q=";
	TextView tv;
	EditText city, state;
	
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		
		setContentView(R.layout.weather);
		
		Button b = (Button) findViewById(R.id.bWeather);
		tv = (TextView) findViewById(R.id.tvWeather);
		city = (EditText) findViewById(R.id.etCity);
		state = (EditText) findViewById(R.id.etState);
		
		b.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String c = city.getText().toString();
		String s = state.getText().toString();
		
		StringBuilder URL = new StringBuilder(baseURL);
		URL.append(c + "," + s + "&mode=xml");
		String fullUrl = URL.toString();
		new GetXML().execute(fullUrl);
	}
	
	private class GetXML extends AsyncTask<String, Integer, String> {
		
		
		String information;
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String fullUrl = arg0[0];
			
			try {
				URL website = new URL(fullUrl);
				//getting xmlreader to parse data
				
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				XMLReader xr = sp.getXMLReader();
				HandlingXMLStuff doingWork = new HandlingXMLStuff();
				xr.setContentHandler(doingWork);
				xr.parse(new InputSource(website.openStream()));
				return doingWork.getInformation();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			tv.setText(result);
		}
		
		
		
	}
	
}
