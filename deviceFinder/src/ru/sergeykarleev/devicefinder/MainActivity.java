package ru.sergeykarleev.devicefinder;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final static String CODE_CALLBACK = "CodeCallBack";
	private final static String CODE_GEO = "CodeGEO";

	private final static String PHONE_FIRST = "FirstPhone";
	private final static String PHONE_SECOND = "SecondPhone";
	private final static String PHONE_THIRD = "ThirdPhone";

	EditText etCodeCallback;
	EditText etCodeGEO;
	EditText etPhone1;
	EditText etPhone2;
	EditText etPhone3;

	SharedPreferences sPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		sPref = getPreferences(MODE_PRIVATE);
		etCodeCallback = (EditText) findViewById(R.id.etCodeCallback);
		etCodeCallback.setText(sPref.getString(CODE_CALLBACK, null));

		etCodeGEO = (EditText) findViewById(R.id.etCodeGEO);
		etCodeGEO.setText(sPref.getString(CODE_GEO, null));

		etPhone1 = (EditText) findViewById(R.id.etPhone1);
		etPhone1.setText(sPref.getString(PHONE_FIRST, null));
		etPhone2 = (EditText) findViewById(R.id.etPhone2);
		etPhone2.setText(sPref.getString(PHONE_SECOND, null));
		etPhone3 = (EditText) findViewById(R.id.etPhone3);
		etPhone3.setText(sPref.getString(PHONE_THIRD, null));
	}

	public void buttonClick(View v) {
		switch (v.getId()) {
		case R.id.btnSave:
			String callback = etCodeCallback.getText().toString();
			String geo = etCodeGEO.getText().toString();
			if ((callback.equals(geo) && callback.isEmpty())
					|| (!callback.equals(geo)))
				try {
					Editor ed = sPref.edit();
					ed.putString(CODE_CALLBACK, etCodeCallback.getText()
							.toString());
					ed.putString(CODE_GEO, etCodeGEO.getText().toString());
					ed.putString(PHONE_FIRST, etPhone1.getText().toString());
					ed.putString(PHONE_SECOND, etPhone2.getText().toString());
					ed.putString(PHONE_THIRD, etPhone3.getText().toString());
					ed.commit();
					Toast.makeText(this,
							getResources().getString(R.string.message_save_ok),
							Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					Toast.makeText(
							this,
							getResources().getString(
									R.string.message_save_error),
							Toast.LENGTH_SHORT).show();
				}
			else {
				Toast.makeText(this,
						getResources().getString(R.string.message_save_duplicate),
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
