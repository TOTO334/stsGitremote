package org.order.service.impl;

import java.util.List;

import org.order.dao.impl.OrderDaoImpl;
import org.order.entity.Order;
import org.order.service.IOrderService;

public class OrderService implements IOrderService {
	private static OrderService orderService = null;

	private OrderService() {
	}

	// 实现一个单例模式
	public static OrderService newInstance() {
		if (orderService == null) {
			orderService = new OrderService();
		}
		return orderService;
	}

	@Override
	public List<Order> queryAllOrder() {
		return OrderDaoImpl.newInstance().queryAllOrder();
	}

	@Override
	public boolean addOrder(Order order) {
		int count = 0;
		OrderDaoImpl orderDao = OrderDaoImpl.newInstance();
		if (order != null && orderDao.queryOrderByName(order.getName()) == null) {
			count = orderDao.addOrder(order);
		}
		return count == 1;
	}

	@Override
	public boolean deleteOrder(Order order) {
		int count = 0;
		count = OrderDaoImpl.newInstance().deleteOrder(order);
		return count == 1;
	}

	@Override
	public boolean updateOrder(Order order) {
		int count = 0;
		count = OrderDaoImpl.newInstance().updateOrder(order);
		return count == 1;
	}


}
