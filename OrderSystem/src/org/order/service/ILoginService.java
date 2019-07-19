package org.order.service;

import org.order.entity.Login;

public interface ILoginService {
	boolean isExist(Login login,boolean isSuper) ;
}
