package com.darkdensity.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.darkdensity.setting.Config;
import com.darkdensity.setting.NLS;
import com.darkdensity.util.ImageLoader;

/* ****************************************
 * Class: Abstractpanel
 * ****************************************
 * Attributes:
 * 	protected NLS nls;
	protected final Color borderColor = new Color(255, 0, 0, 100);
	protected final Color fillColor = new Color(0, 0, 0, 70);
	protected final Color textColor = new Color(76, 196, 40);
	protected final Color titleColor = new Color(164, 180, 248);
	protected final Font font = new Font("Tahoma", Font.BOLD, 12);
	protected Image background = ImageLoader
			.loadImage("res/images/lobby/gameLobby.jpg");
	protected JFrame frame;

 * ****************************************
 * Methods:
 	public Abstractpanel(JFrame frame);
 	protected void initButton(final JButton button, ActionListener listenner);
 	public abstract void paintComponent(Graphics g);
 	public void update(long elapsedTime) ;
 	public void reset() ;
 * */
public abstract class AbstractPanel extends JPanel {
	protected NLS nls;
	protected final Color borderColor = new Color(255, 0, 0, 100);
	protected final Color fillColor = new Color(0, 0, 0, 70);
	protected final Color textColor = new Color(76, 196, 40);
	protected final Color titleColor = new Color(164, 180, 248);
	protected final Font font = new Font("Tahoma", Font.BOLD, 12);
	protected Image background = ImageLoader
			.loadImage(Config.LOOBY_BACKGROUND);
	
	protected JFrame frame;

	public AbstractPanel(JFrame frame) {
		try {
			this.nls = (NLS) Class.forName(Config.NLS_NAME).newInstance();
		} catch (Throwable e) {
			this.nls = new NLS();
		}
		this.frame = frame;
		setLayout(null);
		setSize(frame.getWidth(), frame.getHeight());

	}

	protected void initButton(final JButton button, ActionListener listenner) {
		button.setBorder(null);
		button.setForeground(Color.WHITE);
		button.setIgnoreRepaint(true);
		button.setFocusable(false);
		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.addActionListener(listenner);
	}

	public abstract void paintComponent(Graphics g);

	public void update(long elapsedTime) {
	};

	public void reset() {
	};

}
