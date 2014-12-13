package ru.sergeykarleev.devicefinder;

/**
 * ������ ����� ��������� ��������� �������: 1. ��������� �� SMSMonitor [�����
 * �����������] � [����� ���������] ���������� � ������ � SharedPreferences 2.
 * GPS ON -> ���������� ���������� -> GPS OFF -> SEND SMS 3. ������ callback ��
 * ����� �����������, �������� ��� ���� ����������
 * 
 * 
 * @author SergeyKarleev
 * 
 */
public class AutoBot {
	private String sender;
	private String message;

	/**����������� �������� ���������
	 * @param sender - ����� ����������� ���������
	 * @param message - ���� ���������
	 */
	public AutoBot(String sender, String message) {
		super();
		this.sender = sender;
		this.message = message;
	}

}
