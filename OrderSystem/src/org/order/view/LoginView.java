package org.order.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.order.entity.Login;
import org.order.service.impl.LoginService;
import org.util.viewUtil.ABaseFrame;

public class LoginView extends ABaseFrame {
	private static final long serialVersionUID = 1L;

	private JPanel mainPanel = new JPanel() {
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
//			ImageIcon imageIcon = new ImageIcon("src/OrderImg/bj.jpg");
//			Image image = imageIcon.getImage();
//			g.drawImage(image,0 , 0, LoginView.this.getWidth(),LoginView.this.getHeight(),imageIcon.getImageObserver());			
			super.paint(g);
		}
	};
	private JLabel titleLabel = new JLabel("欢迎登陆订单管理系统");
	private JLabel accountLabel = new JLabel("账号:");
	private JLabel passwordLabel = new JLabel("密码:");

	private JTextField accountField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();

	private JRadioButton generalAdministrator = new JRadioButton("普通管理员");
	private JRadioButton superAdministrator = new JRadioButton("超级管理员");
	ButtonGroup buttonGroup = new ButtonGroup();

	private JButton loginButton = new JButton("登陆");

	public LoginView(String title) {
		super(title);
	}

	@Override
	protected void setFontAndsoOn() {
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 600, 400);
		titleLabel.setFont(new Font("黑体", Font.BOLD, 40));
		titleLabel.setBounds(80, 50, 500, 40);

		Font font = new Font("黑体", Font.BOLD, 30);
		accountLabel.setBounds(150, 150, 100, 40);
		accountLabel.setFont(font);
		accountField.setBounds(260, 150, 200, 40);
		accountField.setFont(font);

		passwordLabel.setBounds(150, 200, 100, 40);
		passwordLabel.setFont(font);
		passwordField.setBounds(260, 200, 200, 40);
		passwordField.setFont(font);
		passwordField.setEchoChar('*');

		generalAdministrator.setBounds(150, 250, 150, 40);
		generalAdministrator.setFont(new Font("黑体", 0, 20));
		generalAdministrator.setName("1");
		generalAdministrator.setSelected(true);
		superAdministrator.setBounds(300, 250, 150, 40);
		superAdministrator.setFont(new Font("黑体", 0, 20));

		loginButton.setBounds(250, 300, 100, 40);
		loginButton.setFont(font);
	}

	@Override
	protected void addElement() {
		mainPanel.add(titleLabel);
		mainPanel.add(accountLabel);
		mainPanel.add(accountField);
		mainPanel.add(passwordLabel);
		mainPanel.add(passwordField);

		buttonGroup.add(generalAdministrator);
		buttonGroup.add(superAdministrator);
		mainPanel.add(generalAdministrator);
		mainPanel.add(superAdministrator);

		mainPanel.add(loginButton);
		this.add(mainPanel);
	}

	@Override
	protected void addListener() {
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String account = accountField.getText();
				String password =String.valueOf(passwordField.getPassword());

				if (account.isEmpty()) {
					JOptionPane.showMessageDialog(LoginView.this, "账号不能为空");
					return;
				} else if (password.isEmpty()) {
					JOptionPane.showMessageDialog(LoginView.this, "密码不能为空");
					return;
				} else {
					boolean isSuper = superAdministrator.isSelected();
					boolean exist = LoginService.newInstance().isExist(new Login(account, password), isSuper);
					if (exist) {
						LoginView.this.dispose();
						new MainView("欢迎登陆-" + account).init();
					} else {
						JOptionPane.showMessageDialog(LoginView.this, "账号或密码错误\n请重新登陆或联系超级管理员");
					}
				}
			}
		});
	}

	@Override
	protected void setFrameSelf() {
		this.setBounds(500, 200, 600, 400);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
