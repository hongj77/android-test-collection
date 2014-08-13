package com.example.blaze;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class Prefs extends PreferenceActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferenceFragment() {
			@Override
			public void onCreate(final Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				addPreferencesFromResource(R.xml.prefs);
			}
		}).commit();
		
		
	}
	
}
