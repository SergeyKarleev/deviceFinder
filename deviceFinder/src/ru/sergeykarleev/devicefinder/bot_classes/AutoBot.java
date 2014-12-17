package ru.sergeykarleev.devicefinder.bot_classes;

import android.content.Context;

/**
 * Потомки данного класса выполняют функции по обратной связи с инициатором в
 * зависимости от запрограммированных функций
 * 
 * @author SergeyKarleev
 * 
 */
public abstract class AutoBot {
	protected String sender;
	protected Context context;

	protected final static String APP_PACKAGE = "ru.sergeykarleev.devicefinder";
	protected final static String APP_MAIN_ACTIVITY = "ru.sergeykarleev.devicefinder.MainActivity";
			
	/**
	 * Конструктор включает параметры
	 * 
	 * @param sender
	 *            - номер отправителя сообщения
	 */
	public AutoBot(String sender, Context context) {
		super();
		this.sender = sender;
		this.context = context;
	}

	/**
	 * Основной метод бота (отправить смс с координатами, перезвонить, отправить
	 * eMail)
	 * Здесь формируется соответствующий Intent и вызывается Activity с данным Intent
	 */
	abstract public void activate();
		

}
