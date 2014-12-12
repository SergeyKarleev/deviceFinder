package ru.sergeykarleev.devicefinder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSMonitor extends BroadcastReceiver {

	public final static String ACTION = "android.provider.Telephony.SMS_RECEIVED";
	public final static String SMS_BODY = "sms_body";

	SharedPreferences sPref;

	@Override
	public void onReceive(Context context, Intent intent) {
		//sPref = context.getSharedPreferences(name, mode)
		if (intent.getAction().equals(SMSMonitor.ACTION)) {

			StringBuilder sb = new StringBuilder();
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Object[] pdus = (Object[]) bundle.get("pdus");
				for (Object pdu : pdus) {
					SmsMessage message = SmsMessage.createFromPdu((byte[]) pdu);
					sb.append(message.getDisplayMessageBody());
				}
			}

			Intent activityIntent = new Intent();
			activityIntent.setClassName("ru.sergeykarleev.devicefinder",
					"ru.sergeykarleev.devicefinder.MainActivity");
			activityIntent.putExtra(SMS_BODY, sb.toString());
			activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(activityIntent);
		}
	}
}
