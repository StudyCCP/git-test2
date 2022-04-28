package com.study.sorm.core;
/**
 * 负责java数据类型和数据库数据类型的互相转换
 * @author ccp
 *
 */
public interface TypeConvertor {
	/**
	 * 将数据库数据类型转化成java数据类型
	 * @param columnType 数据库字段的数据类型
	 * @return java的数据类型
	 */
	public String datebaseType2JavaType(String columnType);
	/**
	 * 负责将java数据类型转换成数据库数据类型
	 * @param javaDataType
	 * @return
	 */
	public String javaType2DatabaseType(String javaDataType);
}
