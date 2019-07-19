package org.order.service;

import java.util.List;

import org.order.entity.Order;
public interface IOrderService {
	//查询
	//全部
	public List<Order> queryAllOrder();
	//添加
	public boolean addOrder(Order order);
	//删除
	public boolean deleteOrder(Order order) ;
	//修改
	public boolean updateOrder(Order order);
}
