package hnks.kitsoft.vn.toado;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class MyLocationListener implements LocationListener{
	
	
	public static double lat;
	public static double lon;
	public static String status;
	public void onLocationChanged(Location loc) {
		lat=loc.getLatitude();

		lon=loc.getLongitude();

		
	}

	public void onProviderDisabled(String arg0) {
		status= "GPS disabled";
		
	}

	public void onProviderEnabled(String arg0) {
		status= "GPS Enabled,no coordinates";
		
	}

	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
	

}
