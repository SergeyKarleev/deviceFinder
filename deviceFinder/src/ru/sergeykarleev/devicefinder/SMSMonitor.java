package ru.sergeykarleev.devicefinder;

import ru.sergeykarleev.devicefinder.bot_classes.AutoBot;
import ru.sergeykarleev.devicefinder.bot_classes.BotCaller;
import ru.sergeykarleev.devicefinder.bot_classes.BotSMSSender;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSMonitor extends BroadcastReceiver {

	private final static String LOG_TAG = "myLogs";

	private final static String ACTION = "android.provider.Telephony.SMS_RECEIVED";
	private final static String SMS_BODY = "sms_body";

	// ��� ����� ��������
	public final static String PREF_NAME = "myPreferences";

	// ����� ����� ��� ����� ��������
	public final static String CODE_CALLBACK = "CodeCallBack";
	public final static String CODE_GEO = "CodeGEO";

	public final static String PHONE_FIRST = "FirstPhone";
	public final static String PHONE_SECOND = "SecondPhone";
	public final static String PHONE_THIRD = "ThirdPhone";

	SharedPreferences sPref;
	AutoBot aBot;
	static Context mContext;

	@Override
	public void onReceive(Context context, Intent intent) {

		sPref = context.getSharedPreferences(this.PREF_NAME,
				Context.MODE_PRIVATE);

		if (!checkPreferences())
			return;

		if (intent.getAction().equals(SMSMonitor.ACTION)) {
			// ������ ����� ����������� � ���� ���������
			String sender = null;
			String message = null;

			StringBuilder sb = new StringBuilder();
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Object[] pdus = (Object[]) bundle.get("pdus");
				for (Object pdu : pdus) {
					SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
					sender = msg.getOriginatingAddress();
					sb.append(msg.getDisplayMessageBody());
				}
			}
			message = sb.toString();

			if (!checkPreferences(sender))
				return;
			
			mContext = context;
			startBot(sender, message.hashCode());
			// ��������� ������� ����� � ���������� ��������
			// startCheckPreferences(sender,message);

			// Intent activityIntent = new Intent();
			// activityIntent.setClassName("ru.sergeykarleev.devicefinder",
			// "ru.sergeykarleev.devicefinder.MainActivity");
			// activityIntent.putExtra(SMS_BODY, sb.toString());
			// activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// context.startActivity(activityIntent);

		}
	}

	private void startBot(String sender, int hashCode) {

		Log.d(LOG_TAG, "StartBot\n"+"Hash:"+hashCode+"\nCALLBACK: "+sPref.getInt(CODE_CALLBACK, 0)+"\nSEND: "+sPref.getInt(CODE_GEO, 0));
		
		if (hashCode == 0)
			return;
		
		if (hashCode == sPref.getInt(CODE_CALLBACK, 0)){
			Log.d(LOG_TAG, "��������� ���� - ���������������");
			abortBroadcast();
			aBot = new BotCaller(sender, mContext);
			aBot.activate();			
		}
			

		if (hashCode == sPref.getInt(CODE_GEO, 0)){
			Log.d(LOG_TAG, "��������� ���� - ������������ SMS");
			abortBroadcast();
			aBot = new BotSMSSender(sender, mContext);
			aBot.activate();
		}
		
		return;
	}

	/**
	 * ����� ��������� ������� ����� � ���������� �������� �� �������
	 */
	private boolean checkPreferences() {
		// �������� ������� ����
		if (sPref.getInt(CODE_GEO, 0) == 0
				&& sPref.getInt(CODE_CALLBACK, 0) == 0) {
			Log.d(LOG_TAG, "������� ����� �� ������");
			return false;
		}

		// �������� ���������� ���������
		if (sPref.getInt(PHONE_FIRST, 0) == 0
				&& sPref.getInt(PHONE_SECOND, 0) == 0
				&& sPref.getInt(PHONE_THIRD, 0) == 0) {
			Log.d(LOG_TAG, "���������� �������� �� ������");
			return false;
		}
		return true;
	}

	/**
	 * ����� ��������� ���������� �������, � �������� ������ sms
	 */
	private boolean checkPreferences(String sender) {
		// �������� ����������� ��������
		if (sPref.getInt(PHONE_FIRST, 0) == sender.hashCode()
				|| sPref.getInt(PHONE_SECOND, 0) == sender.hashCode()
				|| sPref.getInt(PHONE_THIRD, 0) == sender.hashCode()) {
			Log.d(LOG_TAG, "���������� ������� �������");
			return true;
		}		
		Log.d(LOG_TAG, "���������� ������� �� �������");
		return false;
	}
}
