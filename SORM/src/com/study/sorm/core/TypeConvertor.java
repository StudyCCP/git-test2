package com.study.sorm.core;
/**
 * ����java�������ͺ����ݿ��������͵Ļ���ת��
 * @author ccp
 *
 */
public interface TypeConvertor {
	/**
	 * �����ݿ���������ת����java��������
	 * @param columnType ���ݿ��ֶε���������
	 * @return java����������
	 */
	public String datebaseType2JavaType(String columnType);
	/**
	 * ����java��������ת�������ݿ���������
	 * @param javaDataType
	 * @return
	 */
	public String javaType2DatabaseType(String javaDataType);
}
