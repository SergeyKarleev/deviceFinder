package ru.sergeykarleev.devicefinder.bot_classes;

import ru.sergeykarleev.devicefinder.MainActivity;
import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**Класс бота, перезванивающего на телефон инициатора
 * @author skar011
 *
 */
public class BotCaller extends AutoBot {
	
	
	public BotCaller(String sender, Context context) {
		super(sender, context);
	}

	@Override
	public void activate() {
		Intent intent = new Intent(Intent.ACTION_CALL);		
		intent.setData(Uri.parse("tel:"+this.sender));				
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	@Override
	String getCoordinates() {
		return null;
	}

}
