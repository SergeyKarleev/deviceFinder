package ru.sergeykarleev.devicefinder.bot_classes;

/**
 * Потомки данного класса выполняют функции по обратной связи с инициатором в
 * зависимости от запрограммированных функций
 * 
 * @author SergeyKarleev
 * 
 */
public abstract class AutoBot {
	private String sender;

	/**
	 * Конструктор включает параметры
	 * 
	 * @param sender
	 *            - номер отправителя сообщения
	 */
	public AutoBot(String sender) {
		super();
		this.sender = sender;
	}

	/**
	 * Основной метод бота (отправить смс с координатами, перезвонить, отправить
	 * eMail)
	 * Здесь формируется соответствующий Intent и вызывается Activity с данным Intent
	 */
	abstract void activate();
	
	/**
	 * Метод получения координат любым доступным способом.
	 * Вызывается из метода activate()	 
	 * @return строка с координатами
	 */
	abstract String getCoordinates();

}
