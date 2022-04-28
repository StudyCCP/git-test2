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
 * ����������ݿ����б�ṹ����ṹ�Ĺ�ϵ�������Ը��ݱ�ṹ������ṹ
 * @author ccp
 *
 */
public class TableContext {
	/**
	 * ����Ϊkey,����Ϣ����Ϊvalue
	 */
	public static Map<String,TableInfo> tables=new HashMap<>();
	/**
	 * ��po��class����ͱ���Ϣ���������������������
	 */
	public static Map<Class,TableInfo> poClassTableMap=new HashMap<>();  
	static {
	try {
		Connection con=DBManager.getCoon();
		DatabaseMetaData dbmd = con.getMetaData();
		//��ȡ���еı��Ԫ����
		ResultSet tableRs=dbmd.getTables(null, "%", "%", new String[] {"TABLE"});
		while(tableRs.next()) {
			//��ȡ����
			String tableName=(String)tableRs.getObject("TABLE_NAME");
			TableInfo ti=new TableInfo(tableName, new HashMap<String,ColumnInfo>(),new ArrayList<ColumnInfo>());
			tables.put(tableName, ti);
			//��ѯ���������ֶ�
			ResultSet rs=dbmd.getColumns(null, "%", tableName, "%");
			while(rs.next()) {
				ColumnInfo ci=new ColumnInfo(rs.getString("COLUMN_NAME"),
						rs.getString("TYPE_NAME"),0);
				ti.getColumns().put(ci.getName(), ci);
			}
			//��ѯt_user���е�����
			ResultSet rs2=dbmd.getPrimaryKeys(null, "%",tableName);
			while(rs2.next()) {
				ColumnInfo ci2=ti.getColumns().get(rs2.getObject("COLUMN_NAME"));
				ci2.setDataType("1");//����Ϊ��������
				ti.getPriKeys().add(ci2);
			}
			if(ti.getPriKeys().size()>0) {//ȡΨһ��������ʹ�ã����������������Ϊ��
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
