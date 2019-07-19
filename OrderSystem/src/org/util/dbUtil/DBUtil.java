package org.util.dbUtil;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class DBUtil {
	//?useUnicode=true&characterEncoding=utf8 
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/sts";
	private static final String NAME = "root";
	private static final String PASSWORD = "mysql";
	public static Connection con = null;
	public static PreparedStatement psmt = null;
	public static ResultSet res = null;

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(URL, NAME, PASSWORD);
	}

	private static PreparedStatement createPresPreparedStatement(String sql, Object[] parms)
			throws Exception, SQLException {
		con = getConnection();
		psmt = con.prepareStatement(sql);
		if (parms != null)
			for (int i = 1; i <= parms.length; i++) {
				psmt.setObject(i, parms[i - 1]);
			}
		return psmt;
	}

	public static void closeAll(Connection con, PreparedStatement psmt, ResultSet res) {
		try {
			if (con != null)
				con.close();
			if (psmt != null)
				psmt.close();
			if (res != null)
				res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 查询
	public static ResultSet myExecuteQuery(String sql,Object[] parms) throws SQLException, Exception {
		 res = createPresPreparedStatement(sql,parms).executeQuery();
		 return res;
	}
//	修改
	public static int myExecuteUpdate(String sql,Object[] parms) throws SQLException, Exception {
		return createPresPreparedStatement(sql,parms).executeUpdate();
	}
}
