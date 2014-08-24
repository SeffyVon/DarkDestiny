package com.darkdensity.gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.darkdensity.core.GameWorld;
import com.darkdensity.setting.Config;
/*
 * ****************************************
 * Class: VursusPanel
 * ****************************************
 * Attributes:
 * private JButton startBtn, backBtn;
 * ActionListener listenner;
 * *****************************************
 * Methods:
 * public VersusPanel(JFrame parent);
 * private void initButton(JButton button) ;
 * public void paintComponent(Graphics g);
 * public void update(long elapsedTime) ;
 * public void reset();
 */
public class VersusPanel extends AbstractPanel {
	private JButton startBtn, backBtn;

	public VersusPanel(JFrame parent) {
		super(parent);
		setLayout(new FlowLayout());
		startBtn = new JButton(nls.startBtn);
		backBtn = new JButton(nls.backBtn);
		startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.initButton(startBtn);
		this.initButton(backBtn);
	}

	private void initButton(JButton button) {
		super.initButton(button, listenner);
		this.add(button);

	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
	}

	@Override
	public void update(long elapsedTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	ActionListener listenner = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JButton jb = (JButton) e.getSource();
			JFrame jFrame = (JFrame) VersusPanel.this.getRootPane().getParent();
			if (jb.equals(startBtn)) {
				try {
					jFrame.setContentPane(new GameWorld(jFrame,
							Config.GAME_MODE_VS));
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (jb.equals(backBtn)) {

				jFrame.setContentPane(new MainMenuPanel(jFrame));
			}
		}
	};

}
