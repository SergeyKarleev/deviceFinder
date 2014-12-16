package ru.sergeykarleev.devicefinder.bot_classes;

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
	

	/**����������� �������� ���������
	 * @param sender - ����� ����������� ��������� 
	 */
	public AutoBot(String sender) {
		super();
		this.sender = sender;
	
	}

}
