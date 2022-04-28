package com.study.sorm.core;

import java.util.List;

/**
 * 负责查询（对外提供服务的核心类）
 * @author ccp
 *
 */
@SuppressWarnings("all")
public interface Query {
	/**
	 * 直接执行一个DML语句
	 * @param sql sql语句
	 * @param params 参数
	 * @return 执行sql语句影响记录的行数
	 */
	public int executeDML(String sql,Object[]params);
	/**
	 * 将一个对象存储到数据库中
	 * @param obj 要存储的对象
	 */
	public void insert(Object obj);
	/**
	 * 删除clazz标识类对应的表中的记录（制定id）
	 * @param clazz 跟表对应的类的Class对象
	 * @param id 主键的值
	 * @return
	 */
	public int delete(Class clazz,int id);
	/**
	 * 删除对象在数据库中对应的记录（对象所在的类对应到表，对象主键的值对应到记录）
	 * @param obj
	 * @return
	 */
	public int delete(Object obj);
	/**
	 * 更新对象对应的记录，并且值更新指定的对象的值
	 * @param obj 所要更新的对象
	 * @param filedNames 更新的属性列表 
	 * @return 执行sql语句后影响的记录行数
	 */
	public int update(Object obj,String[] filedNames);
	/**
	 * 查询返回多行记录，并将没行记录封装到clazz指定的类的对象中
	 * @param sql 查询语句
	 * @param clazz 封装数据的javabean的Class对象
	 * @param params sql的参数
	 * @return 查询到的结果
	 */
	public List queryRows(String sql,Class clazz,Object[] params);
	/**
	 * 查询返回一行记录，并将没行记录封装到clazz指定的类的对象中
	 * @param sql 查询语句
	 * @param clazz 封装数据的javabean的Class对象
	 * @param params sql的参数
	 * @return 查询到的结果
	 */
	public Object queryUniqueRows(String sql,Class clazz,Object[] params);
	/**
	 * 查询返回一个值（一行一列），并将该值返回
	 * @param sql 查询语句
	 * @param params sql的参数
	 * @return 查询到的结果
	 */
	public Number queryNumber(String sql,Object[] params);
}
