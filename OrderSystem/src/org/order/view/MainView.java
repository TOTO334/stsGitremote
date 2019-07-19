package org.order.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.order.entity.Order;
import org.order.service.impl.OrderService;
import org.util.viewUtil.ABaseFrame;

public class MainView extends ABaseFrame {
	private static final long serialVersionUID = 1L;
	private String[] columnName = { "订单编号", "订单名", "库存", "价格" };
	private static final int COW = 14;
	private List<Order> data = null;
//	面板
	private JPanel buttonPanel = new JPanel();
	private JPanel dataPanel = new JPanel();
//	按钮
	private JButton queryButton = new JButton("查询");
	private JButton insertButton = new JButton("添加");
	private JButton updateButton = new JButton("修改");
	private JButton deleteButton = new JButton("删除");

	private JScrollPane dataScroll = new JScrollPane();
	private DefaultTableModel model = new DefaultTableModel();
	private JTable dataTable = new JTable(model);

	JPopupMenu popupMenu = new JPopupMenu();
	JMenuItem queryItem = new JMenuItem("刷新");
	JMenuItem insertItem = new JMenuItem("添加");
	JMenuItem updateItem = new JMenuItem("修改");
	JMenuItem deleteItem = new JMenuItem("删除");

	public MainView() {
	}

	public MainView(String title) {
		super(title);
	}

	@Override
	public void init() {
		super.init();
//		updataTable();
	}

//	布局
	@Override
	protected void setFontAndsoOn() {
				
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(0, 0, 1000, 80);
		buttonPanel.setBackground(Color.GREEN);

		Font font = new Font("黑体", Font.BOLD, 20);
		queryButton.setBounds(140, 20, 120, 40);
		queryButton.setFont(font);
		insertButton.setBounds(340, 20, 120, 40);
		insertButton.setFont(font);
		updateButton.setBounds(540, 20, 120, 40);
		updateButton.setFont(font);
		deleteButton.setBounds(740, 20, 120, 40);
		deleteButton.setFont(font);

		dataPanel.setBounds(0, 0, 1000, 620);
		dataPanel.setBackground(Color.WHITE);

		dataPanel.setLayout(null);
		dataScroll.setBounds(100, 80, 800, 620);
		// 设置表头
		JTableHeader tableHeader = dataTable.getTableHeader();
		tableHeader.setFont(font);
		tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 40));
		// 数据
		dataTable.setRowHeight(40);
		dataTable.setFont(font);
		// 单选
		dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		选择的背景色
		dataTable.setSelectionBackground(Color.YELLOW);

		Font font1=new Font("黑体",Font.ITALIC , 20);
		popupMenu.setSize(300, 200);
		popupMenu.setFont(font1);
//		popupMenu.setBackground(Color.WHITE);
		queryItem.setFont(font1);
		queryItem.setBackground(Color.WHITE);
		insertItem.setFont(font1);
		insertItem.setBackground(Color.WHITE);
		updateItem.setFont(font1);
		updateItem.setBackground(Color.WHITE);
		deleteItem.setFont(font1);
		deleteItem.setBackground(Color.WHITE);
	}

	@Override
	protected void addElement() {
		buttonPanel.add(queryButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(insertButton);
		buttonPanel.add(updateButton);

		popupMenu.add(queryItem);
		popupMenu.addSeparator();
		popupMenu.add(insertItem);
		popupMenu.addSeparator();
		popupMenu.add(updateItem);
		popupMenu.addSeparator(); //分隔线
		popupMenu.add(deleteItem);

		dataTable.add(popupMenu);

		dataScroll.setViewportView(dataTable);
		dataPanel.add(dataScroll);
		this.add(buttonPanel);
		this.add(dataPanel);
	}

//	更新数据
	private void updataTable() {
		data = OrderService.newInstance().queryAllOrder();
		int size = data.size();
		String[][] value = null;
		if (size > COW) {
			// 转成字符数组
			value = new String[size][4];
		} else {
			value = new String[COW][4];
		}
		for (int i = 0; i < size; i++) {
			value[i] = data.get(i).toStringArray();
		}
		model.setDataVector(value, columnName);
	}

//	被选中的行
	private Order choseCow(JFrame frame, int select) {
		String id = (String) dataTable.getValueAt(select, 0);
		if (id == null) {
			JOptionPane.showMessageDialog(frame, "订单编号错误！");
			return null;
		}
		String name = (String) dataTable.getValueAt(select, 1);
		if (name == null) {
			JOptionPane.showMessageDialog(frame, "商品名称缺失！");
			return null;
		}
		String num = (String) dataTable.getValueAt(select, 2);
		String price = (String) dataTable.getValueAt(select, 3);
		return new Order(Integer.parseInt(id), name, num == null ? 0 : Integer.parseInt(num),
				price == null ? 0 : Float.parseFloat(price));
	}
	@Override
	protected void addListener() {
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddOrderView("新增订单", false).init();
			}
		});
		updateButton.addActionListener(new myUpdateActionListener());
		queryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updataTable();
			}
		});
		deleteButton.addActionListener(new myDeleteActionListener());		
		dataTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		queryItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updataTable();
			}
		});
		insertItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddOrderView("新增订单", false).init();
			}
		});
		updateItem.addActionListener(new myUpdateActionListener());
		deleteItem.addActionListener(new myDeleteActionListener());
	}

	@Override
	protected void setFrameSelf() {
		// 设置展示的位置
		this.setBounds(360, 85, 1000, 720);	
		ImageIcon imageIcon = new ImageIcon("src/OrderImg/bj.jpg");
		this.setIconImage(imageIcon.getImage());
//		不拖拽窗体
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		窗体是否可见
		this.setVisible(true);
	}
	private class myDeleteActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int select = dataTable.getSelectedRow();
			if (select == -1) {
				JOptionPane.showMessageDialog(MainView.this, "你没有选择任意行！");
			} else if (select >= data.size()) {
				JOptionPane.showMessageDialog(MainView.this, "你选择了空白行\n请重新选择！");
			} else {
				if (0 == JOptionPane.showConfirmDialog(MainView.this, "是否确认删除？")) {
					OrderService.newInstance().deleteOrder(choseCow(MainView.this, select));
				}
			}
		}
	}
	private class myUpdateActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int select = dataTable.getSelectedRow();

			if (select == -1) {
				JOptionPane.showMessageDialog(MainView.this, "你没有选择任意行！");
			} else if (select >= data.size()) {
				JOptionPane.showMessageDialog(MainView.this, "你选择了空白行\n请重新选择！");
			} else {
				Order choseCow = choseCow(MainView.this, select);
				if (null != choseCow)
					new UpdateOrderView("修改订单", choseCow, false).init();
			}
		}
	}

}
