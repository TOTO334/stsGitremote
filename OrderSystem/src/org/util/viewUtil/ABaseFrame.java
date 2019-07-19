package org.util.viewUtil;

import javax.swing.JFrame;

public abstract class ABaseFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public ABaseFrame() {
	};

	public ABaseFrame(String title) {
		super(title);
	}

	public void init() {
		this.setFontAndsoOn();
		this.addElement();
		this.addListener();
		this.setFrameSelf();
	}

	// 设置字体 和 位置布局
	protected abstract void setFontAndsoOn();

	// 互相添加到一起 组件之间的关系
	protected abstract void addElement();

	// 添加事件监听
	protected abstract void addListener();

	// 设置属性自己
	protected abstract void setFrameSelf();
}
