package ru.sergeykarleev.devicefinder.bot_classes;

/**
 * Данный класс выполняет следующие функции: 1. Принимает от SMSMonitor [номер
 * отправителя] и [текст сообщения] сравнивает с кодами в SharedPreferences 2.
 * GPS ON -> записывает координаты -> GPS OFF -> SEND SMS 3. Делает callback на
 * номер отправителя, блокируя при этом устройство
 * 
 * 
 * @author SergeyKarleev
 * 
 */
public class AutoBot {
	private String sender;
	

	/**Конструктор включает параметры
	 * @param sender - номер отправителя сообщения 
	 */
	public AutoBot(String sender) {
		super();
		this.sender = sender;
	
	}

}
