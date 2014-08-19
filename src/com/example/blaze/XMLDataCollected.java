package com.example.blaze;

public class XMLDataCollected {
	Double temp = 0.0;
	String city = null;
	
	public void setCity(String c) {
		city = c;
	}
	
	public void setTemp(Double t) {
		temp = t;
	}
	
	public String dataToString() {
		return "In " + city + " the current temp is " + temp + " Kelvin";	
	}
	
}
