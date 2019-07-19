package org.order.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.order.entity.Order;
import org.order.service.impl.OrderService;

public class UpdateOrderView extends DetailsView {
	private static final long serialVersionUID = 1L;

	public UpdateOrderView(String title, boolean idEnable) {
		super(title, idEnable);
	}
	public UpdateOrderView(String title, Order order, boolean idEnable) {
		super(title, order, idEnable);
		this.setFieldText();
	}
	@Override
	protected void addListener() {
		this.sureButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choose = JOptionPane.showConfirmDialog(UpdateOrderView.this, "确认修改？");
				if(choose == 0) {
					boolean flag = OrderService.newInstance().updateOrder(UpdateOrderView.this.getDataFromField(UpdateOrderView.this)) ;  
					if(flag) {
						JOptionPane.showMessageDialog(UpdateOrderView.this, "修改成功");
					}else {
						JOptionPane.showMessageDialog(UpdateOrderView.this, "数据异常\n修改失败!");					
					}
				}				
			}
		});
	}
}
