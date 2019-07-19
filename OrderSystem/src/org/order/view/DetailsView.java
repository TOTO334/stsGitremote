package org.order.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.order.entity.Order;
import org.util.viewUtil.ABaseFrame;

public class DetailsView extends ABaseFrame {
	private static final long serialVersionUID = 1L;
	protected Order order = null;
	private JPanel mainPanel = new JPanel();
	// 四个属性
	private JLabel idLabel = new JLabel("商品编号:");
	private JLabel nameLabel = new JLabel("商品名称:");
	private JLabel numLabel = new JLabel("商品数量:");
	private JLabel priceLabel = new JLabel("商品价格:");
	// 变量值
	private JTextField idField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField numField = new JTextField();
	private JTextField priceField = new JTextField();

	protected JButton sureButton = new JButton("确认");

	public DetailsView(boolean idEnable) {
		idField.setEnabled(idEnable);
	}

	public DetailsView(String title, boolean idEnable) {
		super(title);
		idField.setEnabled(idEnable);
	}

	public DetailsView(String title, Order order, boolean idEnable) {
		super(title);
		this.order = order;
		idField.setEnabled(idEnable);
	}

	@Override
	protected void setFontAndsoOn() {
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 500, 400);

		Font font = new Font("黑体", Font.BOLD, 20);
		idLabel.setBounds(100, 100, 150, 40); // 150,250
		idLabel.setFont(font);
		idField.setBounds(220, 100, 230, 40);
		idField.setFont(font);

		nameLabel.setBounds(100, 150, 150, 40); // 150,250
		nameLabel.setFont(font);
		nameField.setBounds(220, 150, 230, 40);
		nameField.setFont(font);

		numLabel.setBounds(100, 200, 150, 40); // 150,250
		numLabel.setFont(font);
		numField.setBounds(220, 200, 230, 40);
		numField.setFont(font);
		priceLabel.setBounds(100, 250, 150, 40); // 150,250
		priceLabel.setFont(font);
		priceField.setBounds(220, 250, 230, 40);
		priceField.setFont(font);
		sureButton.setBounds(400, 350, 70, 40);
		sureButton.setBackground(Color.GREEN);
	}

	@Override
	protected void addElement() {
		mainPanel.add(idLabel);
		mainPanel.add(idField);
		mainPanel.add(nameLabel);
		mainPanel.add(nameField);
		mainPanel.add(numLabel);
		mainPanel.add(numField);
		mainPanel.add(priceLabel);
		mainPanel.add(priceField);
		mainPanel.add(sureButton);
		this.add(mainPanel);
	}

	protected void clearAllFiled() {
		idField.setText("");
		nameField.setText("");
		numField.setText("");
		priceField.setText("");
	}

	public void setFieldText() {
		idField.setText(order.getId() + "");
		nameField.setText(order.getName());
		numField.setText(order.getNum() + "");
		priceField.setText(order.getPrice() + "");
	}

	public Order getDataFromField(JFrame frame) {
		if (order == null)
			order = new Order();

		String id = this.idField.getText();
		String name = this.nameField.getText();
		String num = this.numField.getText();
		int resNum = 0;
		String price = this.priceField.getText();
		float resPrice = 0;

//		if (id.isEmpty()) {
//			JOptionPane.showMessageDialog(frame, "订单编号不能为空！");
//			return null;
//		} else {
//			try {
//				resId = Integer.parseInt(id);
//			} catch (Exception e) {
//				JOptionPane.showMessageDialog(frame, "订单编号必须为数字！");
//				return null;
//			}
//		}
		if (name.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "商品名称缺失！");
			return null;
		}

		if (!price.isEmpty())
			try {
				resNum = Integer.parseInt(num);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "数量填写错误");
				return null;
			}
		if (!price.isEmpty())
			try {
				resPrice = Float.parseFloat(price);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "价格填写错误");
				return null;
			}
		if(!id.isEmpty()) {
			order.setId(Integer.parseInt(id));
		}
		order.setName(name);
		order.setNum(resNum);
		order.setPrice(resPrice);
		return order;
	}

	@Override
	protected void addListener() {

	}

	@Override
	protected void setFrameSelf() {
		this.setBounds(350, 200, 500, 450);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
