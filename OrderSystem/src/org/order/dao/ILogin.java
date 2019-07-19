package org.order.dao;

import org.order.entity.Login;

public interface ILogin {
	public int isExist(Login login,boolean isSuper) ;
}
