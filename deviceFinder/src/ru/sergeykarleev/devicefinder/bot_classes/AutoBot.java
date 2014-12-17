package ru.sergeykarleev.devicefinder.bot_classes;

/**
 * ������� ������� ������ ��������� ������� �� �������� ����� � ����������� �
 * ����������� �� ������������������� �������
 * 
 * @author SergeyKarleev
 * 
 */
public abstract class AutoBot {
	private String sender;

	/**
	 * ����������� �������� ���������
	 * 
	 * @param sender
	 *            - ����� ����������� ���������
	 */
	public AutoBot(String sender) {
		super();
		this.sender = sender;
	}

	/**
	 * �������� ����� ���� (��������� ��� � ������������, �����������, ���������
	 * eMail)
	 * ����� ����������� ��������������� Intent � ���������� Activity � ������ Intent
	 */
	abstract void activate();
	
	/**
	 * ����� ��������� ��������� ����� ��������� ��������.
	 * ���������� �� ������ activate()	 
	 * @return ������ � ������������
	 */
	abstract String getCoordinates();

}
