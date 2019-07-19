package org.order.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.order.dao.ILogin;
import org.order.entity.Login;
import org.util.dbUtil.DBUtil;

public class LoginImpl implements ILogin {
	@Override
	public int isExist(Login login, boolean isSuper) {
		String sql = null;
		if (isSuper) {
			sql = "select count(*) from admin where account =? and password = ?";
		} else {
			sql = "select count(*) from login where account =? and password = ?";
		}
		Object[] parms = { login.getAccount(), login.getPassword() };
		try {
			ResultSet res = DBUtil.myExecuteQuery(sql, parms);
			if (res.next())
				return res.getInt(1);
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
