package ru.sergeykarleev.devicefinder.bot_classes;

import android.content.Context;

/**
 * ������� ������� ������ ��������� ������� �� �������� ����� � ����������� �
 * ����������� �� ������������������� �������
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
	 * ����������� �������� ���������
	 * 
	 * @param sender
	 *            - ����� ����������� ���������
	 */
	public AutoBot(String sender, Context context) {
		super();
		this.sender = sender;
		this.context = context;
	}

	/**
	 * �������� ����� ���� (��������� ��� � ������������, �����������, ���������
	 * eMail)
	 * ����� ����������� ��������������� Intent � ���������� Activity � ������ Intent
	 */
	abstract public void activate();
		

}
