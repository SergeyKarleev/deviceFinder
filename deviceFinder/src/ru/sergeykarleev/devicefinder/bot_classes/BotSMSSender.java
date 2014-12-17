package ru.sergeykarleev.devicefinder.bot_classes;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * ����� ����, ������������� ���������� ����������� ��� ����������
 * 
 * @author skar011
 * 
 */
public class BotSMSSender extends AutoBot implements LocationListener {

	private final static String LOG_TAG = "myLogs";

	LocationManager lm;

	public BotSMSSender(String sender, Context context) {
		super(sender, context);

		// lm = (LocationManager)
		// context.getSystemService(Context.LOCATION_SERVICE);
		// lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
		// this);
		// lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}

	@Override
	public void activate() {

		if (getCoordinates() != null) {
			Intent intent = new Intent(Intent.ACTION_SENDTO);
			intent.setData(Uri.parse("tel:" + this.sender));
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.putExtra("sms_body", getCoordinates());
		}
		// activityIntent.setClassName("ru.sergeykarleev.devicefinder",
		// "ru.sergeykarleev.devicefinder.MainActivity");
		// activityIntent.putExtra(SMS_BODY, sb.toString());
		// activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// context.startActivity(activityIntent);

	}

	private String getCoordinates() {
		
		return "����������!!!";
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