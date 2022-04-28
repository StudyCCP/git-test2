package com.study.sorm.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.study.sorm.bean.Configuration;

/**
 * 根据配置信息，维持连接对象的管理（增加连接池功能）
 * @author ccp
 *
 */
public class DBManager {
	private static Configuration conf;
	static {//静态代码块
		Properties prop=new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conf=new Configuration();
		conf.setDriver(prop.getProperty("driver"));
		conf.setPoPackage(prop.getProperty("poPackage"));
		conf.setPwd(prop.getProperty("pwd"));
		conf.setSrcPath(prop.getProperty("srcPath"));
		conf.setUrl(prop.getProperty("url"));
		conf.setUser(prop.getProperty("user"));
		conf.setUsingDB(prop.getProperty("usingDB"));
	}
	public static Connection getCoon() {
		Connection coon=null;
		try {
			Class.forName(conf.getDriver());
			coon=DriverManager.getConnection(conf.getUrl(),conf.getUser(),conf.getPwd());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coon;
	}
	public static void close(ResultSet rs,PreparedStatement ps,Connection coon) {
		try {
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(ps!=null) {
				ps.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(coon!=null) {
				coon.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
