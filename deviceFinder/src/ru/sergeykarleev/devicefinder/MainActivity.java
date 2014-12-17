package ru.sergeykarleev.devicefinder;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final String LOG_TAG = "myLogs";

	EditText etCodeCallback;
	EditText etCodeGEO;
	EditText etPhone1;
	EditText etPhone2;
	EditText etPhone3;
	
	private final static String HIDE_STRING = "**********";
	
	
	SharedPreferences sPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		sPref = getSharedPreferences(SMSMonitor.PREF_NAME, MODE_PRIVATE);

		etCodeCallback = (EditText) findViewById(R.id.etCodeCallback);
		etCodeGEO = (EditText) findViewById(R.id.etCodeGEO);
		etPhone1 = (EditText) findViewById(R.id.etPhone1);
		etPhone2 = (EditText) findViewById(R.id.etPhone2);
		etPhone3 = (EditText) findViewById(R.id.etPhone3);

		int codeCallback = sPref.getInt(SMSMonitor.CODE_CALLBACK, 0);
		int codeGEO = sPref.getInt(SMSMonitor.CODE_GEO, 0);
		int phone1 = sPref.getInt(SMSMonitor.PHONE_FIRST, 0);
		int phone2 = sPref.getInt(SMSMonitor.PHONE_SECOND, 0);
		int phone3 = sPref.getInt(SMSMonitor.PHONE_THIRD, 0);

		etCodeCallback.setText(fieldFilling(codeCallback));
		etCodeGEO.setText(fieldFilling(codeGEO));
		etPhone1.setText(fieldFilling(phone1));
		etPhone2.setText(fieldFilling(phone2));
		etPhone3.setText(fieldFilling(phone3));
	}

	private String fieldFilling(int value) {
		Log.d(LOG_TAG, "load hash: "+value);
		if (value != 0) {
			return HIDE_STRING;
		}
		return null;
	}

	public void buttonClick(View v) {
		switch (v.getId()) {
		case R.id.btnSave:
			String callback = etCodeCallback.getText().toString();
			String geo = etCodeGEO.getText().toString();

			if (callback.equals(geo) && !callback.equals(HIDE_STRING)
					&& !callback.isEmpty())

			{
				Toast.makeText(
						this,
						getResources().getString(
								R.string.message_save_duplicate),
						Toast.LENGTH_SHORT).show();
				break;
			}

			try {
				Editor ed = sPref.edit();

				putEditor(ed, SMSMonitor.CODE_CALLBACK, etCodeCallback
							.getText().toString());
				putEditor(ed, SMSMonitor.CODE_GEO, etCodeGEO
						.getText().toString());
				putEditor(ed, SMSMonitor.PHONE_FIRST, etPhone1
						.getText().toString());
				putEditor(ed, SMSMonitor.PHONE_SECOND, etPhone2
						.getText().toString());
				putEditor(ed, SMSMonitor.PHONE_THIRD, etPhone3
						.getText().toString());

				ed.commit();
				Toast.makeText(this,
						getResources().getString(R.string.message_save_ok),
						Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				Toast.makeText(this,
						getResources().getString(R.string.message_save_error),
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.btnExit:
			finish();
			break;
		default:
			break;
		}
	}

	private void putEditor(Editor ed,String key, String value){
		if (!value.equals(HIDE_STRING)){
			ed.putInt(key, value.hashCode());
			Log.d(LOG_TAG, key+": "+value+" - hash: "+value.hashCode());
		}
		return;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
