package edu.util;

import java.sql.SQLException;

/*
 * C3P0辅助类库
 * 
 */
public class C3P0Fun {
	
	private static javax.sql.DataSource dsC3P0 = null;
	/**
	 * 获取conn对象
	 * 
	 */
	public static java.sql.Connection getConnection(){
		java.sql.Connection conn = null;
		try {
			if(dsC3P0==null) {
				build();
			}
			if(dsC3P0!=null) {
				return dsC3P0.getConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	private static javax.sql.DataSource build() {
		// TODO Auto-generated method stub
		System.out.println("C3P0.");
		if(dsC3P0 != null) {
			return dsC3P0;
		}
		try {
			Class<?> clazz = Class.forName("com.mchange.v2.c3p0.ComboPooledDataSource");
			dsC3P0 = (javax.sql.DataSource) clazz.newInstance();
			/*
			 * 1.配合c3p0-config.xml,使用c3p0-config.xml的默认配置来创建数据源
			 */
			//ds = new com.mchange.v2.c3p0.ComboPooledDataSource();
			/*
			 * 2.配合c3p0-config.xml,使用c3p0-config.xml的命名配置来春卷关键数据源
			 */
			//ds = new com.mchange.v2.c3p0.ComboPooledDataSource("Oracle");
			/*
			 * 3.通过代码创建C3P0数据库连接池
			 * 
			 */
			//ds = new com.mchange.v2.c3p0.ComboPooledDataSource();
			//ds.setDriverClass("com.mysql.jdbl.Driver");
			//ds.setJdbcUrl("jdbc:mysql://localhost:23306/?");
			//ds.setUser("root");
			//ds.setPassword("root");
			//ds.setInitialPoolSize(2);
			//ds.setMinPoolSize(2);
			//ds.setMaxPoolSize(5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsC3P0;
	}
}
