package com.study.sorm.core;

import java.util.List;

/**
 * �����ѯ�������ṩ����ĺ����ࣩ
 * @author ccp
 *
 */
@SuppressWarnings("all")
public interface Query {
	/**
	 * ֱ��ִ��һ��DML���
	 * @param sql sql���
	 * @param params ����
	 * @return ִ��sql���Ӱ���¼������
	 */
	public int executeDML(String sql,Object[]params);
	/**
	 * ��һ������洢�����ݿ���
	 * @param obj Ҫ�洢�Ķ���
	 */
	public void insert(Object obj);
	/**
	 * ɾ��clazz��ʶ���Ӧ�ı��еļ�¼���ƶ�id��
	 * @param clazz �����Ӧ�����Class����
	 * @param id ������ֵ
	 * @return
	 */
	public int delete(Class clazz,int id);
	/**
	 * ɾ�����������ݿ��ж�Ӧ�ļ�¼���������ڵ����Ӧ��������������ֵ��Ӧ����¼��
	 * @param obj
	 * @return
	 */
	public int delete(Object obj);
	/**
	 * ���¶����Ӧ�ļ�¼������ֵ����ָ���Ķ����ֵ
	 * @param obj ��Ҫ���µĶ���
	 * @param filedNames ���µ������б� 
	 * @return ִ��sql����Ӱ��ļ�¼����
	 */
	public int update(Object obj,String[] filedNames);
	/**
	 * ��ѯ���ض��м�¼������û�м�¼��װ��clazzָ������Ķ�����
	 * @param sql ��ѯ���
	 * @param clazz ��װ���ݵ�javabean��Class����
	 * @param params sql�Ĳ���
	 * @return ��ѯ���Ľ��
	 */
	public List queryRows(String sql,Class clazz,Object[] params);
	/**
	 * ��ѯ����һ�м�¼������û�м�¼��װ��clazzָ������Ķ�����
	 * @param sql ��ѯ���
	 * @param clazz ��װ���ݵ�javabean��Class����
	 * @param params sql�Ĳ���
	 * @return ��ѯ���Ľ��
	 */
	public Object queryUniqueRows(String sql,Class clazz,Object[] params);
	/**
	 * ��ѯ����һ��ֵ��һ��һ�У���������ֵ����
	 * @param sql ��ѯ���
	 * @param params sql�Ĳ���
	 * @return ��ѯ���Ľ��
	 */
	public Number queryNumber(String sql,Object[] params);
}
