package ru.sergeykarleev.devicefinder.bot_classes;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;

/**
 *  ласс бота, отправл€ющего координаты посредством смс инициатору
 * 
 * @author skar011
 * 
 */
public class BotSMSSender extends AutoBot {

	private final static String LOG_TAG = "myLogs";

	MyLocator mLocator;
	String coordinates;

	public BotSMSSender(String sender, Context context) {
		super(sender, context);
		mLocator = new MyLocator(context);
	}

	@Override
	public void activate() {	
		mLocator.getCoordinates();
	}
	
	

	private class MyLocator implements LocationListener {
		LocationManager lm;
		Context mContext;

		/**
		 * @param lm
		 * @param mContext
		 */
		public MyLocator(Context mContext) {
			super();
			this.mContext = mContext;

			lm = (LocationManager) context
					.getSystemService(Context.LOCATION_SERVICE);
		}

		public void getCoordinates() {
			lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
					this);
			lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
			turnGPSOn();
		}

		@Override
		public void onLocationChanged(Location location) {
			coordinates = String.format("Latitude: %.3f,   Logitude%.3fn",
					location.getLatitude(), location.getLongitude());
			lm.removeUpdates(this);
			
			if (coordinates != null) {
				SmsManager sms = SmsManager.getDefault();
				sms.sendTextMessage(sender, null, coordinates, null, null);
			}
			
			turnGPSOff();
			//TODO:  ак-то надо убить экземпл€р MyLocator
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		@Override
		public void onProviderEnabled(String provider) {

		}

		@Override
		public void onProviderDisabled(String provider) {

		}

		private void turnGPSOn() {

			String provider = Settings.Secure.getString(
					mContext.getContentResolver(),
					Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

			if (!provider.contains("gps")) { 
				// if gps is disabled
				final Intent poke = new Intent();
				poke.setClassName("com.android.settings",
						"com.android.settings.widget.SettingsAppWidgetProvider");
				poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
				poke.setData(Uri.parse("3"));
				mContext.sendBroadcast(poke);
			}
		}

		private void turnGPSOff() {
			String provider = Settings.Secure.getString(
					mContext.getContentResolver(),
					Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

			if (provider.contains("gps")) { 
				// if gps is enabled
				final Intent poke = new Intent();
				poke.setClassName("com.android.settings",
						"com.android.settings.widget.SettingsAppWidgetProvider");
				poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
				poke.setData(Uri.parse("3"));
				mContext.sendBroadcast(poke);
			}
		}

	}
}
