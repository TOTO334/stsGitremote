package org.order.service.impl;

import org.order.dao.ILogin;
import org.order.dao.impl.LoginImpl;
import org.order.entity.Login;
import org.order.service.ILoginService;

public class LoginService implements ILoginService{
	private static LoginService loginService= new LoginService();
	private ILogin loginDao = new LoginImpl();
	
	private  LoginService() {
	}
	public static LoginService newInstance() {
		return loginService;
	}
	
	@Override
	public boolean isExist(Login login,boolean isSuper) {
		return loginDao.isExist(login, isSuper) == 1;
	}

}
