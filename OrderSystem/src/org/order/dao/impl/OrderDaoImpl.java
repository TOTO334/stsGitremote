package org.order.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.order.dao.IOrderDao;
import org.order.entity.Order;
import org.util.dbUtil.DBUtil;

public class OrderDaoImpl implements IOrderDao {
	private static OrderDaoImpl orderDaoImpl = null;
	private OrderDaoImpl() {}
	public static OrderDaoImpl newInstance() {
		if(orderDaoImpl==null) {
			orderDaoImpl = new OrderDaoImpl();
		}
		return orderDaoImpl;
	}
	@Override
	public List<Order> queryAllOrder() {
		String sql = "select id,name,num,price from myorder";
		ArrayList<Order> orderList = new ArrayList<Order>();
		try {
			ResultSet result = DBUtil.myExecuteQuery(sql, null);
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int num = result.getInt("num");
				float price = result.getFloat("price");
				orderList.add(new Order(id, name, num, price));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(DBUtil.con, DBUtil.psmt, DBUtil.res);
		}
		return orderList;
	}

	@Override
	public Order queryOrderById(int id) {
		String sql = "select id,name,num,price from myorder where id=?";
		Order order = null;
		Object[] prms = {id};
		try {
			ResultSet result = DBUtil.myExecuteQuery(sql,prms);
			if(result.next()) {
				int uid = result.getInt("id");
				String name = result.getString("name");
				int num = result.getInt("num");
				float price = result.getFloat("price");
				order = new Order(uid, name, num, price);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(DBUtil.con, DBUtil.psmt, DBUtil.res);
		}
		return order;
	}

	@Override
	public int addOrder(Order order) {
		String sql = "insert into myorder(name,num,price) values(?,?,?)";
		Object[] parms = {order.getName(),order.getNum(), order.getPrice()};
		int count = 0;
		try {
			count = DBUtil.myExecuteUpdate(sql, parms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int deleteOrder(Order order) {
		String sql = "delete from myorder where id=?";
		int count = 0;
		Object[] parms= {order.getId()};
		try {
			count = DBUtil.myExecuteUpdate(sql, parms);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return count;
	}

	@Override
	public int updateOrder(Order order) {
		int count = 0;
		String sql = "update myorder set name=?,num=?,price=? where id=?";
		Object[] parms= {order.getName(),order.getNum(),order.getPrice(),order.getId()};
		try {
			count = DBUtil.myExecuteUpdate(sql, parms);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return count;
	}
	@Override
	public Order queryOrderByName(String orderName) {
		String sql = "select id,name,num,price from myorder where name=?";
		Order order = null;
		Object[] prms = {orderName};
		try {
			ResultSet result = DBUtil.myExecuteQuery(sql,prms);
			if(result.next()) {
				int uid = result.getInt("id");
				String name = result.getString("name");
				int num = result.getInt("num");
				float price = result.getFloat("price");
				order = new Order(uid, name, num, price);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeAll(DBUtil.con, DBUtil.psmt, DBUtil.res);
		}
		return order;
	}

}
