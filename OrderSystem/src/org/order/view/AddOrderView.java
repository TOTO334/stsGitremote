package org.order.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.order.entity.Order;
import org.order.service.impl.OrderService;

public class AddOrderView extends DetailsView {
	private static final long serialVersionUID = 1L;
	public AddOrderView(boolean inEnable){
		super(inEnable);
	}
	public AddOrderView(String title,boolean inEnable){
		super(title,null,inEnable);
	}
	@Override
	protected void addListener() {
		this.sureButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(AddOrderView.this, "确认添加？");
				if(choose == 0) {
					Order data = AddOrderView.this.getDataFromField(AddOrderView.this);
					
					if(data != null) {						
						boolean flag = OrderService.newInstance().addOrder(data) ; 
						
						if(flag) {
							JOptionPane.showMessageDialog(AddOrderView.this, "添加成功");
							AddOrderView.this.clearAllFiled();
						}else {
							JOptionPane.showMessageDialog(AddOrderView.this, "订单号冲突\n添加失败!");					
						}
					}
				}
			}
		});		
	}	
}
