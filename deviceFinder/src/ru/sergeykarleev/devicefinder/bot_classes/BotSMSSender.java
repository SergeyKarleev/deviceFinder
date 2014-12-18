package ru.sergeykarleev.devicefinder.bot_classes;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

/**
 *  ласс бота, отправл€ющего координаты посредством смс инициатору
 * 
 * @author skar011
 * 
 */
public class BotSMSSender extends AutoBot implements LocationListener {

	private final static String LOG_TAG = "myLogs";

	LocationManager lm;
	StringBuilder coordGPS = new StringBuilder();
	StringBuilder coordNetwork = new StringBuilder();

	public BotSMSSender(String sender, Context context) {
		super(sender, context);

		lm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}

	@Override
	public void activate() {

		if (getCoordinates() != null) {
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(sender, null, getCoordinates(), null, null);
		}

	}

	private String getCoordinates() {

		return "Coordinates";
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

}
