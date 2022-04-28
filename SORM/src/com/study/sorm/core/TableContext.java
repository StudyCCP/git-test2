package com.study.sorm.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.study.sorm.bean.ColumnInfo;
import com.study.sorm.bean.TableInfo;

/**
 * 负责管理数据库所有表结构和类结构的关系，并可以根据表结构生成类结构
 * @author ccp
 *
 */
public class TableContext {
	/**
	 * 表明为key,表信息对象为value
	 */
	public static Map<String,TableInfo> tables=new HashMap<>();
	/**
	 * 将po的class对象和表信息对象关联起来，便于重用
	 */
	public static Map<Class,TableInfo> poClassTableMap=new HashMap<>();  
	static {
	try {
		Connection con=DBManager.getCoon();
		DatabaseMetaData dbmd = con.getMetaData();
		//获取所有的表的元数据
		ResultSet tableRs=dbmd.getTables(null, "%", "%", new String[] {"TABLE"});
		while(tableRs.next()) {
			//获取表名
			String tableName=(String)tableRs.getObject("TABLE_NAME");
			TableInfo ti=new TableInfo(tableName, new HashMap<String,ColumnInfo>(),new ArrayList<ColumnInfo>());
			tables.put(tableName, ti);
			//查询表中所有字段
			ResultSet rs=dbmd.getColumns(null, "%", tableName, "%");
			while(rs.next()) {
				ColumnInfo ci=new ColumnInfo(rs.getString("COLUMN_NAME"),
						rs.getString("TYPE_NAME"),0);
				ti.getColumns().put(ci.getName(), ci);
			}
			//查询t_user表中的主键
			ResultSet rs2=dbmd.getPrimaryKeys(null, "%",tableName);
			while(rs2.next()) {
				ColumnInfo ci2=ti.getColumns().get(rs2.getObject("COLUMN_NAME"));
				ci2.setDataType("1");//设置为主键类型
				ti.getPriKeys().add(ci2);
			}
			if(ti.getPriKeys().size()>0) {//取唯一主键方便使用，如果是联合主键则为空
				ti.setOnlyPriKey(ti.getPriKeys().get(0));
				
			}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Set<String> set=tables.keySet();
		for (String string : set) {
			System.out.println(string);
			
		}
		
	}
}
